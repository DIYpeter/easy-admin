package com.diypeter.service.sys.pojo.dto.sysmenu;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysMenuDeleteDto {
    @NotBlank(message = "请选择要删除的菜单")
    private String id;
}

