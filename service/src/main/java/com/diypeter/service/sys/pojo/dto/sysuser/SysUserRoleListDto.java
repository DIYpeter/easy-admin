package com.diypeter.service.sys.pojo.dto.sysuser;

import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserRoleListDto {

    /**
     * 用户id
     */
    private String id;

    /**
     * 角色ID
     */
    private List<String> roleId;
}

