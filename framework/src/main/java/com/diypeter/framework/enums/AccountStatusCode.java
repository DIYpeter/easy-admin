package com.diypeter.framework.enums;

public enum AccountStatusCode {
    ENABLE("enable", "启用"),
    DISABLE("disable", "禁用"),
    ;
    public final String code;
    public final String name;

    AccountStatusCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
