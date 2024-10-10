package com.diypeter.service.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.diypeter.service.sys.mapper.SysMenuMapper;
import com.diypeter.service.sys.mapstruct.SysMenuMapStruct;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuAddDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuEditDto;
import com.diypeter.service.sys.pojo.dto.sysmenu.SysMenuListDto;
import com.diypeter.service.sys.pojo.po.SysMenu;
import com.diypeter.service.sys.service.SysMenuService;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.diypeter.service.sys.pojo.po.table.SysMenuTableDef.SYS_MENU;

/**
 * @author: diypeter
 * @date: 2024/9/25 22:20
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenuListDto> getMenuTree() {

        // 获取全部菜单
        List<SysMenu> menuList = list();

        // 组装成树结构
        return buildMenuTree(menuList, "0");
    }

    @Override
    public Boolean addSysMenu(SysMenuAddDto sysMenuAddDto) {

        SysMenu sysMenu = SysMenuMapStruct.INSTANCE.sysMenuAddDtoToSysMenu(sysMenuAddDto);

        return save(sysMenu);
    }

    /**
     * 编辑菜单
     *
     * @param sysMenuEditDto
     * @return
     */
    @Override
    public Boolean editSysMenu(SysMenuEditDto sysMenuEditDto) {

        SysMenu sysMenu = SysMenuMapStruct.INSTANCE.sysMenuEditDtoToSysMenu(sysMenuEditDto);

        return updateById(sysMenu, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSysMenu(SysMenuDeleteDto sysMenuDeleteDto) {

        // 删除本级和下级
        // 获取需要删除的id列表
        List<SysMenu> childMenuList = getChildMenuList(sysMenuDeleteDto.getId());

        List<String> removeIds = childMenuList.stream().map(SysMenu::getId).collect(Collectors.toList());
        // 添加自己
        removeIds.add(sysMenuDeleteDto.getId());
        return removeByIds(removeIds);
    }

    /**
     * 组装菜单的树结构
     */
    private List<SysMenuListDto> buildMenuTree(List<SysMenu> menuList, String pId) {

        // 获取当前层节点
        List<SysMenu> sysMenus = menuList.stream().filter(f -> f.getPid().equals(pId)).toList();

        List<SysMenuListDto> r = new ArrayList<>();
        // 组装数据 循环调用 下钻数据
        for (SysMenu sysMenu : sysMenus) {
            SysMenuListDto sysMenuListDto = SysMenuMapStruct.INSTANCE.sysMenuToSysMenuListDto(sysMenu);
            // 下钻
            List<SysMenuListDto> sysMenuListDtos = buildMenuTree(menuList, sysMenuListDto.getId());
            // 将下钻结果保存到当前节点的字节点中
            sysMenuListDto.setChildren(sysMenuListDtos);
            // 将当前节点数据写入当前层级的list中
            r.add(sysMenuListDto);
        }

        // 返回当前层级的菜单
        return r;
    }

    /**
     * 查询下级菜单列表
     */
    private List<SysMenu> getChildMenuList(String id) {

        List<SysMenu> list = list(
                QueryWrapper.create()
                        .where(SYS_MENU.PID.eq(id))
        );
        if (CollectionUtil.isEmpty(list)) {
            return list;
        }

        // 循环获取下级
        for (SysMenu sysMenu : list) {
            // 搜索下级菜单
            List<SysMenu> childMenuList = getChildMenuList(sysMenu.getId());
            // 添加到当前list中
            list.addAll(childMenuList);
        }

        return list;
    }
}
