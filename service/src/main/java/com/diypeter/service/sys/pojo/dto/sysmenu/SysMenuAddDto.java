package com.diypeter.service.sys.pojo.dto.sysmenu;

import com.diypeter.framework.enums.MenuTypeCode;
import com.diypeter.framework.validator.InEnumValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author: diypeter
 * @date: 2024/9/10 18:24
 */
@Data
public class SysMenuAddDto {
    /**
     * 父级id
     */
    @NotBlank(message = "请选择父级菜单")
    private String pId = "0";
    /**
     * 菜单标题
     */
    @NotBlank(message = "请填写菜单标题")
    private String title;

    /**
     * 前端路由路径
     */
    @NotBlank(message = "请填写前端路由路径")
    private String path;
    /**
     * 前端路由名称
     */
    @NotBlank(message = "请填写前端路由名称")
    private String name;
    /**
     * 前端组件名称
     */
    @NotBlank(message = "请填写前端组件名称")
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
    @NotBlank(message = "请选择前端是否隐藏菜单")
    private String hideFlag;
    /**
     * 前端需要高亮的菜单 菜单的path
     */
    private String activeMenu;
    /**
     * 前端是否全屏
     */
    @NotBlank(message = "请选择前端是否全屏")
    private String fullFlag;
    /**
     * 前端是否固定在标签页中
     */
    @NotBlank(message = "请选择前端是否固定在标签页中")
    private String affixFlag;
    /**
     * 前端路由是否需要缓存
     */
    @NotBlank(message = "请选择前端路由是否需要缓存")
    private String keepAliveFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单类型
     */
    @InEnumValue(enumClass = MenuTypeCode.class,message = "不支持的菜单类型")
    @NotBlank(message = "请选择菜单类型")
    private String menuTypeCd;
    /**
     * 按钮权限
     */
    @NotBlank(message = "请输入按钮权限")
    private String permissions;
}

