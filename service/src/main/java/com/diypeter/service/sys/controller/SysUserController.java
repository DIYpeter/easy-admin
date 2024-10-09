package com.diypeter.service.sys.controller;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.entity.R;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserAddDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserEditDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserSelectDto;
import com.diypeter.service.sys.pojo.po.SysUser;
import com.diypeter.service.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: diypeter
 * @date: 2024/9/10 17:08
 */
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/pageList")
    public R<PageR<SysUser>> pageList(@Validated SysUserSelectDto sysUserSelectDto) {
        PageR<SysUser> r = sysUserService.pageList(sysUserSelectDto);
        return R.ok(r);
    }

    @PostMapping("/add")
    public R<Boolean> add(@Validated @RequestBody SysUserAddDto sysUserAddDto) {
        Boolean r = sysUserService.addSysUser(sysUserAddDto);
        return R.ok(r);
    }


    @PostMapping("/edit")
    public R<Boolean> edit(@Validated @RequestBody SysUserEditDto sysUserEditDto) {
        Boolean r = sysUserService.editSysUser(sysUserEditDto);
        return R.ok(r);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@Validated @RequestBody SysUserDeleteDto sysUserDeleteDto) {
        Boolean r = sysUserService.deleteSysUser(sysUserDeleteDto);
        return R.ok(r);
    }

}
