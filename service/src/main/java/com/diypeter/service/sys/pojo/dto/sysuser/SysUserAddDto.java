package com.diypeter.service.sys.pojo.dto.sysuser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserAddDto {
    @NotBlank(message = "请输入账户名称")
    private String accountName;
    @NotBlank(message = "请输入手机号")
    private String phone;
    @NotBlank(message = "请输入密码")
    private String password;
}

