package com.diypeter.framework.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: diypeter
 * @date: 2024/9/28 19:27
 */
@Slf4j
public class InEnumValidator implements ConstraintValidator<InEnumValue, Object> {

    private Class<? extends Enum> enumClass;

    @Override
    public void initialize(InEnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        // 判断当前枚举是否支持校验功能
        boolean assignableFrom = InEnum.class.isAssignableFrom(enumClass);

        if (assignableFrom) {
            // 支持 调用校验方法
            try {
                Method validate = enumClass.getMethod("validate", Object.class);
                return (boolean) validate.invoke(enumClass.getEnumConstants()[0], value);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                log.error("枚举参数校验异常", e);
            }
            return false;
        } else {
            // 不支持 返回校验失败
            return false;
        }
    }

}
