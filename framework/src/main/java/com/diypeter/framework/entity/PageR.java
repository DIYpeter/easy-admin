package com.diypeter.framework.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:21
 */
@Data
public class PageR<T> implements Serializable {
    private long current;
    private long limit;
    private long total;
    private List<T> rows;

    public PageR(long current, long limit, long total, List<T> rows) {
        this.current = current;
        this.limit = limit;
        this.total = total;
        this.rows = rows;
    }
}
