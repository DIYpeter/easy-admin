package com.diypeter.service.sys.controller;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.entity.R;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleAddMenuButtonDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleEditDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleMenuListDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleQueryMenuButtonDto;
import com.diypeter.service.sys.pojo.dto.sysrole.SysRoleSelectDto;
import com.diypeter.service.sys.pojo.po.SysRole;
import com.diypeter.service.sys.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: diypeter
 * @date: 2024/9/25 14:55
 */
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService sysRoleService;


    @GetMapping("/pageList")
    public R<PageR<SysRole>> pageList(@Validated SysRoleSelectDto sysUserSelectDto) {
        PageR<SysRole> r = sysRoleService.pageList(sysUserSelectDto);
        return R.ok(r);
    }

    @PostMapping("/add")
    public R<Boolean> add(@Validated @RequestBody SysRoleAddDto sysRoleAddDto) {
        Boolean r = sysRoleService.addSysRole(sysRoleAddDto);
        return R.ok(r);
    }


    @PostMapping("/edit")
    public R<Boolean> edit(@Validated @RequestBody SysRoleEditDto sysRoleEditDto) {
        Boolean r = sysRoleService.editSysRole(sysRoleEditDto);
        return R.ok(r);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@Validated @RequestBody SysRoleDeleteDto sysRoleDeleteDto) {
        Boolean r = sysRoleService.deleteSysRole(sysRoleDeleteDto);
        return R.ok(r);
    }

    /**
     * 查询角色的权限信息
     */
    @GetMapping("/query-menu-button")
    public R<SysRoleMenuListDto> queryMenuButton(@Validated SysRoleQueryMenuButtonDto sysRoleQueryMenuButtonDto) {
        SysRoleMenuListDto r = sysRoleService.queryMenuButton(sysRoleQueryMenuButtonDto);
        return R.ok(r);
    }

    /**
     * 给角色添加权限
     */
    @PostMapping("/add-menu-button")
    public R<Boolean> addMenuButton(@Validated @RequestBody SysRoleAddMenuButtonDto sysRoleAddMenuButtonDto) {
        Boolean r = sysRoleService.addMenuButton(sysRoleAddMenuButtonDto);
        return R.ok(r);
    }

}
