package com.diypeter.framework.enums;

import com.diypeter.framework.validator.InEnum;

public enum BooleanCode implements InEnum {
    TRUE("true", "是"),
    FALSE("false", "否"),
    ;
    public final String code;
    public final String name;

    BooleanCode(String code, String name) {
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
        BooleanCode[] menuTypeCodes = BooleanCode.values();
        for (BooleanCode menuTypeCode : menuTypeCodes) {
            if (menuTypeCode.code.equals(inValue)) {
                return true;
            }
        }
        return false;
    }
}
