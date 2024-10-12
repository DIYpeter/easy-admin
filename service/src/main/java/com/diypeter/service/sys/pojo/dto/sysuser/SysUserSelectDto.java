package com.diypeter.service.sys.pojo.dto.sysuser;

import com.diypeter.framework.entity.dto.PageQuery;
import com.diypeter.framework.enums.AccountStatusCode;
import com.diypeter.framework.validator.InEnumValue;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserSelectDto extends PageQuery {
    private String accountName;
    private String phone;
    @InEnumValue(enumClass = AccountStatusCode.class, message = "账户类型参数不合法")
    private String accountStatusCd;
}

