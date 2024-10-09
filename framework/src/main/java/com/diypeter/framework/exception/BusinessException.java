package com.diypeter.framework.exception;

import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/25 17:08
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
