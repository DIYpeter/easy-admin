package com.diypeter.service.sys.service;

import com.diypeter.framework.entity.PageR;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserAddDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserEditDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserSelectDto;
import com.diypeter.service.sys.pojo.po.SysUser;
import com.mybatisflex.core.service.IService;

public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询
     * @param sysUserSelectDto
     * @return
     */
    PageR<SysUser> pageList(SysUserSelectDto sysUserSelectDto);

    /**
     * 新增账号
     * @param sysUserAddDto
     * @return
     */
    Boolean addSysUser(SysUserAddDto sysUserAddDto);

    /**
     * 编辑账号
     * @param sysUserEditDto
     * @return
     */
    Boolean editSysUser(SysUserEditDto sysUserEditDto);

    /**
     * 删除账号
     * @param sysUserDeleteDto
     * @return
     */
    Boolean deleteSysUser(SysUserDeleteDto sysUserDeleteDto);
}
