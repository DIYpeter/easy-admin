package com.diypeter.service.sys.pojo.dto.sysmenu;

import lombok.Data;

import java.util.List;

/**
 * @author: diypeter
 * @date: 2024/9/25 22:26
 */
@Data
public class SysMenuListDto {

    private String id;

    /**
     * 父级id
     */
    private String pid;
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
    private String hideFlag;
    /**
     * 前端需要高亮的菜单 菜单的path
     */
    private String activeMenu;
    /**
     * 前端是否全屏
     */
    private String fullFlag;
    /**
     * 前端是否固定在标签页中
     */
    private String affixFlag;
    /**
     * 前端路由是否需要缓存
     */
    private String keepAliveFlag;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单类型
     */
    private String menuTypeCd;
    /**
     * 按钮权限
     */
    private String permissions;

    private List<SysMenuListDto> children;

}
