package com.diypeter.service.sys.pojo.dto.sysrole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysRoleAddMenuButtonDto {

    /**
     * 角色ID
     */
    @NotBlank(message = "请选择角色")
    private String id;

    @Size(min = 1, message = "请选择要添加的菜单或按钮权限")
    private List<String> menuIds;

}

