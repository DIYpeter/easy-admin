package com.diypeter.service.sys.service.impl;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.exception.BusinessException;
import com.diypeter.service.sys.mapper.SysRoleMapper;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleEditDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleSelectDto;
import com.diypeter.service.sys.pojo.po.SysRole;
import com.diypeter.service.sys.service.SysRoleService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.diypeter.service.sys.pojo.po.table.SysRoleTableDef.SYS_ROLE;

/**
 * @author: diypeter
 * @date: 2024/9/25 14:59
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

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
    public Boolean deleteSysRole(SysRoleDeleteDto sysRoleDeleteDto) {
        return removeByIds(sysRoleDeleteDto.getIds());
    }
}
