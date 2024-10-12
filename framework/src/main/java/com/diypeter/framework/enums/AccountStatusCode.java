package com.diypeter.framework.enums;

import com.diypeter.framework.validator.InEnum;

public enum AccountStatusCode implements InEnum {
    ENABLE("enable", "启用"),
    DISABLE("disable", "禁用"),
    ;
    public final String code;
    public final String name;

    AccountStatusCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean validate(Object value) {

        // 判断类型是否正确
        if (!(value instanceof String inValue)) {
            // 类型不正确
            return false;
        }

        // 判断是否合法
        AccountStatusCode[] accountStatusCodes = AccountStatusCode.values();
        for (AccountStatusCode accountStatusCode : accountStatusCodes) {
            if (accountStatusCode.code.equals(inValue)) {
                return true;
            }
        }
        return false;
    }
}
