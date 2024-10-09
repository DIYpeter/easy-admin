package com.diypeter.framework.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: diypeter
 * @date: 2024/9/10 17:14
 */
@Data
public class R<T> implements Serializable {

    public Integer code;
    public String msg;
    public T data;

    public R() {
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> ok() {
        return ok((T) "{}");
    }

    public static <T> R<T> ok(T data) {
        return ok(data, "成功");
    }

    public static <T> R<T> ok(T data, String msg) {
        R<T> r = new R<>();
        r.code = 200;
        r.msg = msg;
        r.data = data;
        return r;
    }

    public static <T> R<T> error(Integer code) {
        return error(code, "失败");
    }

    public static <T> R<T> error(Integer code, String msg) {
        R<T> r = new R<>();
        r.code = code;
        r.msg = msg;
        r.data = (T) "{}";
        return r;
    }

}
