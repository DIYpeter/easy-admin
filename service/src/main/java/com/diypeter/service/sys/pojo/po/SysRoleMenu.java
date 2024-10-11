package com.diypeter.service.sys.pojo.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * 系统角色-菜单表
 *
 * @author: diypeter
 * @date: 2024/9/10 14:44
 */
@Data
@Table("sys_role_menu")
public class SysRoleMenu {

    @Id
    private String id;
    private String roleId;
    private String menuId;

}
