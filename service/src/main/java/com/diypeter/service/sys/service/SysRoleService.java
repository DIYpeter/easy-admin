package com.diypeter.service.sys.service;

import com.diypeter.framework.entity.PageR;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleEditDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleSelectDto;
import com.diypeter.service.sys.pojo.po.SysRole;
import com.mybatisflex.core.service.IService;

/**
 * @author: diypeter
 * @date: 2024/9/25 14:58
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询
     *
     * @param sysRoleSelectDto
     * @return
     */
    PageR<SysRole> pageList(SysRoleSelectDto sysRoleSelectDto);

    /**
     * 添加系统角色
     *
     * @param sysRoleAddDto
     * @return
     */
    Boolean addSysRole(SysRoleAddDto sysRoleAddDto);

    /**
     * 编辑角色
     *
     * @param sysRoleEditDto
     * @return
     */
    Boolean editSysRole(SysRoleEditDto sysRoleEditDto);

    /**
     * 删除角色
     *
     * @param sysRoleDeleteDto
     * @return
     */
    Boolean deleteSysRole(SysRoleDeleteDto sysRoleDeleteDto);
}
