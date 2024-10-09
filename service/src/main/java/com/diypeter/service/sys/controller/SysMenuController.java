package com.diypeter.service.sys.controller;

import com.diypeter.framework.entity.R;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuAddDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuEditDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuListDto;
import com.diypeter.service.sys.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 17:08
 */
@RestController
@RequestMapping("/sysMenu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("/getMenuTree")
    public R<List<SysMenuListDto>> getMenuTree() {
        List<SysMenuListDto> r = sysMenuService.getMenuTree();
        return R.ok(r);
    }

    /**
     * 新增菜单
     */
    @PostMapping("/add")
    public R<Boolean> add(@Validated @RequestBody SysMenuAddDto sysMenuAddDto) {
        Boolean b = sysMenuService.addSysMenu(sysMenuAddDto);
        return R.ok(b);
    }

    /**
     * 编辑菜单
     */
    @PostMapping("/edit")
    public R<Boolean> edit(@Validated @RequestBody SysMenuEditDto sysMenuEditDto) {
        Boolean b = sysMenuService.editSysMenu(sysMenuEditDto);
        return R.ok(b);
    }

    /**
     * 删除菜单
     */
    @PostMapping("/delete")
    public R<Boolean> delete(@Validated @RequestBody SysMenuDeleteDto sysMenuDeleteDto) {
        Boolean r = sysMenuService.deleteSysMenu(sysMenuDeleteDto);
        return R.ok(r);
    }

}
