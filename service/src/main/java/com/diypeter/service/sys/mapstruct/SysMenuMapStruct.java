package com.diypeter.service.sys.mapstruct;

import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuAddDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuEditDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuListDto;
import com.diypeter.service.sys.pojo.po.SysMenu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: diypeter
 * @date: 2024/9/28 11:05
 */
@Mapper
public interface SysMenuMapStruct {

    SysMenuMapStruct INSTANCE = Mappers.getMapper(SysMenuMapStruct.class);

    SysMenuListDto sysMenuToSysMenuListDto(SysMenu sysMenu);

    SysMenu sysMenuAddDtoToSysMenu(SysMenuAddDto sysMenuAddDto);
    SysMenu sysMenuEditDtoToSysMenu(SysMenuEditDto sysMenuEditDto);


}
