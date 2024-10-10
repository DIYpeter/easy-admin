package com.diypeter.service.sys.pojo.dto.sysmenu;

import com.diypeter.framework.enums.BooleanCode;
import com.diypeter.framework.enums.MenuTypeCode;
import com.diypeter.framework.validator.InEnumValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysMenuEditDto {
    @NotBlank(message = "请选择要编辑的菜单")
    private String id;
    /**
     * 菜单标题
     */
    private String title;

    /**
     * 前端路由路径
     */
    private String path;
    /**
     * 前端路由名称
     */
    private String name;
    /**
     * 前端组件名称
     */
    private String component;
    /**
     * 前端路由图标
     */
    private String icon;
    /**
     * 前端外链地址
     */
    private String link;
    /**
     * 前端是否隐藏菜单
     */
    @InEnumValue(enumClass = BooleanCode.class, message = "前端是否隐藏菜单参数不合法")
    private String hideFlag;
    /**
     * 前端需要高亮的菜单 菜单的path
     */
    private String activeMenu;
    /**
     * 前端是否全屏
     */
    @InEnumValue(enumClass = BooleanCode.class, message = "前端是否全屏参数不合法")
    private String fullFlag;
    /**
     * 前端是否固定在标签页中
     */
    @InEnumValue(enumClass = BooleanCode.class, message = "前端是否固定在标签页中参数不合法")
    private String affixFlag;
    /**
     * 前端路由是否需要缓存
     */
    @InEnumValue(enumClass = BooleanCode.class, message = "前端路由是否需要缓存参数不合法")
    private String keepAliveFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单类型
     */
    @InEnumValue(enumClass = MenuTypeCode.class, message = "不支持的菜单类型")
    private String menuTypeCd;
    /**
     * 按钮权限
     */
    private String permissions;
}

