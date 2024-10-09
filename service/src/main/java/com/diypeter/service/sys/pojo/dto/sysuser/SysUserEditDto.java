package com.diypeter.service.sys.pojo.dto.sysuser;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserEditDto {
    @NotBlank(message = "请选择要编辑的账户")
    private String id;
    private String accountName;
    private String phone;
}

