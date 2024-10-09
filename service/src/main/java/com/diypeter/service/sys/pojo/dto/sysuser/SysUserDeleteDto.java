package com.diypeter.service.sys.pojo.dto.sysuser;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysUserDeleteDto {
    @Size(min = 1,message = "请选择要删除的账户")
    private List<String> ids;
}

