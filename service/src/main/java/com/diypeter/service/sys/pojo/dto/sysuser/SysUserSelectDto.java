package com.diypeter.service.sys.pojo.dto.sysuser;

import com.diypeter.framework.entity.dto.PageQuery;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserSelectDto extends PageQuery {
    private String accountName;
    private String phone;
    private String accountStatusCd;
}

