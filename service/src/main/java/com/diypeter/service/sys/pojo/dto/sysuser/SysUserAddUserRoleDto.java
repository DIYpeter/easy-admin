package com.diypeter.service.sys.pojo.dto.sysuser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserAddUserRoleDto {

    /**
     * 用户id
     */
    @NotBlank(message = "请选择用户")
    private String id;

    @Size(min = 1, message = "请选择要添加的角色")
    private List<String> roleIds;
}

