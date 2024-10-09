package com.diypeter.service.sys.pojo.dto.sysrole;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysRoleAddDto {
    @NotBlank(message = "请输入角色名称")
    private String roleName;
}

