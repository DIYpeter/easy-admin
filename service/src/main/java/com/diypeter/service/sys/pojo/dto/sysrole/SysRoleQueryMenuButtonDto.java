package com.diypeter.service.sys.pojo.dto.sysrole;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysRoleQueryMenuButtonDto {
    /**
     * 角色ID
     */
    @NotBlank(message = "请选择角色")
    private String id;
}

