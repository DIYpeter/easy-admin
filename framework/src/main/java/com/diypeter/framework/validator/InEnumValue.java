package com.diypeter.framework.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举校验
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InEnumValidator.class)
public @interface InEnumValue {

    String message() default "参数不合法";

    Class<? extends Enum<?>> enumClass();

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
