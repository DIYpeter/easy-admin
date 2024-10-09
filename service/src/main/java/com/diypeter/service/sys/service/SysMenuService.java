package com.diypeter.service.sys.service;

import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuAddDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuEditDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuListDto;
import com.diypeter.service.sys.pojo.po.SysMenu;
import com.mybatisflex.core.service.IService;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/25 14:58
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单树
     *
     * @return
     */
    List<SysMenuListDto> getMenuTree();

    /**
     * 新增菜单
     *
     * @param sysMenuAddDto
     * @return
     */
    Boolean addSysMenu(SysMenuAddDto sysMenuAddDto);

    /**
     * 编辑菜单
     *
     * @param sysMenuEditDto
     * @return
     */
    Boolean editSysMenu(SysMenuEditDto sysMenuEditDto);

    /**
     * 删除菜单
     * @param sysMenuDeleteDto
     * @return
     */
    Boolean deleteSysMenu(SysMenuDeleteDto sysMenuDeleteDto);
}
