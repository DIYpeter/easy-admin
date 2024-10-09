package com.diypeter.service.sys.pojo.dto.sysrole;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysRoleDeleteDto {
    @Size(min = 1, message = "请选择要删除的角色")
    private List<String> ids;
}

