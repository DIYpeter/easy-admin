package com.diypeter.framework.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:25
 */
@Data
public class PageQuery {
    @NotNull(message = "分页参数page不能为空")
    private Integer page = 1;
    @NotNull(message = "分页参数limit不能为空")
    private Integer limit = 10;
}
