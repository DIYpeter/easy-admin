package com.diypeter.service.sys.pojo.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 14:44
 */
@Data
@Table("sys_user_role")
public class SysUserRole {

    @Id
    private String id;
    private String userId;
    private String roleId;

}
