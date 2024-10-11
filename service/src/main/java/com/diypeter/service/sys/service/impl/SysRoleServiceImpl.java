package com.diypeter.service.sys.service.impl;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.exception.BusinessException;
import com.diypeter.service.sys.mapper.SysRoleMapper;
import com.diypeter.service.sys.mapper.SysRoleMenuMapper;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddMenuButtonDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleEditDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleMenuListDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleQueryMenuButtonDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleSelectDto;
import com.diypeter.service.sys.pojo.po.SysRole;
import com.diypeter.service.sys.pojo.po.SysRoleMenu;
import com.diypeter.service.sys.service.SysRoleService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.diypeter.service.sys.pojo.po.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.diypeter.service.sys.pojo.po.table.SysRoleTableDef.SYS_ROLE;

/**
 * @author: diypeter
 * @date: 2024/9/25 14:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public PageR<SysRole> pageList(SysRoleSelectDto sysRoleSelectDto) {

        Page<SysRole> page = this.page(
                new Page<>(sysRoleSelectDto.getPage(), sysRoleSelectDto.getLimit()),
                QueryWrapper.create()
                        .where(SYS_ROLE.ROLE_NAME.like(sysRoleSelectDto.getRoleName(), If::hasText))
        );

        return new PageR<>(page.getPageNumber(), page.getPageSize(), page.getTotalRow(), page.getRecords());
    }

    /**
     * 添加系统角色
     *
     * @param sysRoleAddDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addSysRole(SysRoleAddDto sysRoleAddDto) {

        // roleName是否重复
        long count = this.count(
                QueryWrapper.create()
                        .where(SYS_ROLE.ROLE_NAME.eq(sysRoleAddDto.getRoleName()))
        );
        if (count > 0) {
            throw new BusinessException(999, "角色名称重复");
        }

        SysRole sysRole = new SysRole();
        sysRole.setRoleName(sysRoleAddDto.getRoleName());

        return save(sysRole);
    }

    /**
     * 编辑角色
     *
     * @param sysRoleEditDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editSysRole(SysRoleEditDto sysRoleEditDto) {

        // roleName是否重复
        long count = this.count(
                QueryWrapper.create()
                        .where(SYS_ROLE.ROLE_NAME.eq(sysRoleEditDto.getRoleName()))
                        .and(SYS_ROLE.ID.ne(sysRoleEditDto.getId()))
        );
        if (count > 0) {
            throw new BusinessException(999, "角色名称重复");
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(sysRoleEditDto.getId());
        sysRole.setRoleName(sysRoleEditDto.getRoleName());

        return updateById(sysRole, true);
    }

    /**
     * 删除角色
     *
     * @param sysRoleDeleteDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSysRole(SysRoleDeleteDto sysRoleDeleteDto) {
        return removeByIds(sysRoleDeleteDto.getIds());
    }

    // ****************** 角色-菜单 关联 开始*********************************************

    /**
     * 查询可配置的权限和拥有的权限
     *
     * @param sysRoleQueryMenuButtonDto
     * @return
     */
    @Override
    public SysRoleMenuListDto queryMenuButton(SysRoleQueryMenuButtonDto sysRoleQueryMenuButtonDto) {

        // 获取当前角色拥有的权限
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(
                QueryWrapper.create()
                        .where(SYS_ROLE_MENU.ROLE_ID.eq(sysRoleQueryMenuButtonDto.getId()))
        );
        // 组合成列表返回
        List<String> menuIdList = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).toList();

        SysRoleMenuListDto dto = new SysRoleMenuListDto();
        dto.setId(sysRoleQueryMenuButtonDto.getId());
        dto.setMenuId(menuIdList);

        return dto;
    }

    /**
     * 给角色赋予菜单和按钮权限
     *
     * @param sysRoleAddMenuButtonDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addMenuButton(SysRoleAddMenuButtonDto sysRoleAddMenuButtonDto) {

        // 删除历史关联关系
        sysRoleMenuMapper.deleteByQuery(
                QueryWrapper.create()
                        .where(SYS_ROLE_MENU.ROLE_ID.eq(sysRoleAddMenuButtonDto.getId()))
        );

        // 增加关联关系
        List<SysRoleMenu> list = sysRoleAddMenuButtonDto.getMenuIds().stream()
                .filter(StringUtils::hasText)
                .map(d -> {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(sysRoleAddMenuButtonDto.getId());
                    sysRoleMenu.setMenuId(d);
                    return sysRoleMenu;
                })
                .toList();

        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(999, "未选择要添加的权限");
        }

        sysRoleMenuMapper.insertBatch(list);

        // todo 刷新用户的角色权限

        return true;
    }
    // ****************** 角色-菜单 关联 结束 *********************************************

}
