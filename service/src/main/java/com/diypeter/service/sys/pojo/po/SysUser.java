package com.diypeter.service.sys.pojo.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 14:44
 */
@Data
@Table("sys_user")
public class SysUser {

    @Id
    private String id;
    private String accountName;
    private String password;
    private String phone;
    private String accountStatusCd;
    private String delFlag;

}
