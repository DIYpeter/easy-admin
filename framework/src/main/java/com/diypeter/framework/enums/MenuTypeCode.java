package com.diypeter.framework.enums;

import com.diypeter.framework.validator.InEnum;

public enum MenuTypeCode implements InEnum {
    MENU("menu", "菜单"),
    BUTTON("button", "按钮"),
    ;
    public final String code;
    public final String name;

    MenuTypeCode(String code, String name) {
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
        MenuTypeCode[] menuTypeCodes = MenuTypeCode.values();
        for (MenuTypeCode menuTypeCode : menuTypeCodes) {
            if (menuTypeCode.code.equals(inValue)) {
                return true;
            }
        }
        return false;
    }
}
