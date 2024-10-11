package com.diypeter.service.sys.pojo.dto.sysrole;

import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/10/10 19:11
 */
@Data
public class SysRoleMenuListDto {

    /**
     * 角色id
     */
    private String id;

    private List<String> menuId;

}
