package com.diypeter.service.sys.service.impl;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.enums.AccountStatusCode;
import com.diypeter.framework.exception.BusinessException;
import com.diypeter.service.sys.mapper.SysRoleMenuMapper;
import com.diypeter.service.sys.mapper.SysUserMapper;
import com.diypeter.service.sys.mapper.SysUserRoleMapper;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserAddDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserAddUserRoleDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserEditDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserQueryUserRoleDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserRoleListDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserSelectDto;
import com.diypeter.service.sys.pojo.po.SysUser;
import com.diypeter.service.sys.pojo.po.SysUserRole;
import com.diypeter.service.sys.service.SysUserService;
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

import static com.diypeter.service.sys.pojo.po.table.SysUserRoleTableDef.SYS_USER_ROLE;
import static com.diypeter.service.sys.pojo.po.table.SysUserTableDef.SYS_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleMenuMapper sysRoleMenuMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public PageR<SysUser> pageList(SysUserSelectDto sysUserSelectDto) {

        Page<SysUser> page = this.page(
                new Page<>(sysUserSelectDto.getPage(), sysUserSelectDto.getLimit()),
                QueryWrapper.create()
                        .where(SYS_USER.ACCOUNT_NAME.like(sysUserSelectDto.getAccountName(), If::hasText))
                        .and(SYS_USER.PHONE.like(sysUserSelectDto.getPhone(), If::hasText))
        );

        return new PageR<>(page.getPageNumber(), page.getPageSize(), page.getTotalRow(), page.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addSysUser(SysUserAddDto sysUserAddDto) {

        SysUser sysUser = new SysUser();
        sysUser.setAccountName(sysUserAddDto.getAccountName());
        sysUser.setPassword(sysUserAddDto.getPassword());
        sysUser.setPhone(sysUserAddDto.getPhone());
        sysUser.setAccountStatusCd(AccountStatusCode.ENABLE.code);

        return save(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editSysUser(SysUserEditDto sysUserEditDto) {

        SysUser sysUser = new SysUser();
        sysUser.setId(sysUserEditDto.getId());
        sysUser.setAccountName(sysUserEditDto.getAccountName());
        sysUser.setPhone(sysUserEditDto.getPhone());

        return updateById(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSysUser(SysUserDeleteDto sysUserDeleteDto) {
        return removeByIds(sysUserDeleteDto.getIds());
    }

    /**
     * 查询用户拥有的角色
     *
     * @param sysUserQueryUserRoleDto
     * @return
     */
    @Override
    public SysUserRoleListDto queryUserRole(SysUserQueryUserRoleDto sysUserQueryUserRoleDto) {

        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectListByQuery(
                QueryWrapper.create()
                        .where(SYS_USER_ROLE.USER_ID.eq(sysUserQueryUserRoleDto.getId()))
        );

        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).toList();

        SysUserRoleListDto dto = new SysUserRoleListDto();
        dto.setId(sysUserQueryUserRoleDto.getId());
        dto.setRoleId(roleIdList);

        return dto;
    }

    /**
     * 为用户添加角色
     *
     * @param sysUserAddUserRoleDto
     * @return
     */
    @Override
    public Boolean addUserRole(SysUserAddUserRoleDto sysUserAddUserRoleDto) {

        // 删除历史关联关系
        sysUserRoleMapper.deleteByQuery(
                QueryWrapper.create()
                        .where(SYS_USER_ROLE.USER_ID.eq(sysUserAddUserRoleDto.getId()))
        );

        // 添加关联关系
        List<SysUserRole> list = sysUserAddUserRoleDto.getRoleIds().stream()
                .filter(StringUtils::hasText)
                .map(d -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUserAddUserRoleDto.getId());
                    sysUserRole.setRoleId(d);
                    return sysUserRole;
                })
                .toList();

        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(999, "未选择要添加的角色");
        }

        sysUserRoleMapper.insertBatch(list);

        // todo 刷新用户角色权限

        return true;
    }
}
