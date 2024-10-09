package com.diypeter.service.sys.pojo.dto.sysrole;

import com.diypeter.framework.entity.dto.PageQuery;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/25 15:01
 */
@Data
public class SysRoleSelectDto extends PageQuery {
    private String roleName;
}
