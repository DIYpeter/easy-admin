package com.diypeter.service.sys.pojo.dto.sysrole;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysRoleEditDto {
    @NotBlank(message = "请选择要编辑的角色")
    private String id;
    @NotBlank(message = "请输入角色名称")
    private String roleName;
}

