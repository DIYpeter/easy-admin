package com.diypeter.framework.mvc;

import cn.hutool.json.JSONUtil;
import com.diypeter.framework.entity.R;
import com.diypeter.framework.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author: diypeter
 * @date: 2024/9/25 15:15
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常拦截
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> allExceptionHandler(Exception ex) {

        log.error(ex.getMessage(), ex);

        R<Object> error = R.error(999, "服务异常");
        return new ResponseEntity<String>(JSONUtil.toJsonStr(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public R<String> handleBusinessException(BusinessException ex) {
        return R.error(ex.getCode(), ex.getMessage());
    }


    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public R<String> handleValidException(MethodArgumentNotValidException ex) {
        String mapBindMsg = mapBindMsg(ex.getBindingResult());
        return R.error(999, mapBindMsg);
    }


    private String mapBindMsg(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(StringUtils::hasText)
                .sorted()
                .collect(Collectors.joining(","));
    }

}
