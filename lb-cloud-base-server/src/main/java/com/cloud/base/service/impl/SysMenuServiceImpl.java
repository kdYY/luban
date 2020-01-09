package com.cloud.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.dto.MenuDTO;
import com.cloud.base.entity.SysMenu;
import com.cloud.base.mapper.SysMenuMapper;
import com.cloud.base.service.ISysMenuService;
import com.cloud.base.service.ISysRoleMenuService;
import com.cloud.base.vo.MenuTreeVo;
import com.cloud.core.constant.MenuConstant;
import com.cloud.core.exception.BaseException;
import com.cloud.core.utils.MmcUtil;
import com.cloud.core.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @Override
    public boolean save(SysMenu sysMenu) {
        // 菜单校验
        verifyForm(sysMenu);
        return super.save(sysMenu);
    }

    @Override
    public boolean updateMenuById(MenuDTO entity) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(entity, sysMenu);
        // 菜单校验
        verifyForm(sysMenu);
        return this.updateById(sysMenu);
    }

    @Override
    public List<SysMenu> selectMenuTree(Integer uid) {

        LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper = Wrappers.<SysMenu>query().lambda();
        sysMenuLambdaQueryWrapper.
                select(SysMenu::getMenuId, SysMenu::getName, SysMenu::getPerms, SysMenu::getPath, SysMenu::getParentId, SysMenu::getComponent, SysMenu::getIsFrame, SysMenu::getIcon, SysMenu::getSort, SysMenu::getType, SysMenu::getDelFlag, SysMenu::getAlwaysShow, SysMenu::getHidden, SysMenu::getRedirect, SysMenu::getKeepAlive, SysMenu::getRedirect);
        // 所有人有权限看到 只是没有权限操作而已 暂定这样
        if (uid != 0) {
            List<Integer> menuIdList = roleMenuService.getMenuIdByUserId(uid);
            sysMenuLambdaQueryWrapper.in(SysMenu::getMenuId, menuIdList);
        }

        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = baseMapper.selectList(sysMenuLambdaQueryWrapper);

        menus.forEach(menu -> {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                menu.setKey(menu.getMenuId());
                if (MmcUtil.exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        });
        sysMenus.sort((o1, o2) -> o1.getSort().compareTo(o2.getSort()));
        MmcUtil.findChildren(sysMenus, menus, 0);
        return sysMenus;
    }

    @Override
    public SysMenu getMenuById(Integer parentId) {
        return baseMapper.selectOne(Wrappers.<SysMenu>lambdaQuery().select(SysMenu::getType).eq(SysMenu::getMenuId, parentId));
    }

    @Override
    public List<String> findPermsByUserId(Integer userId) {
        return baseMapper.findPermsByUserId(userId);
    }

    @Override
    public R removeMenuById(Serializable id) {
        List<Integer> idList =
                this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id)).stream().map(SysMenu::getMenuId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(idList)) {
            return R.error("菜单含有下级不能删除");
        }
        return R.ok(this.updateById(new SysMenu().setMenuId((int) id).setDelFlag("1")));
    }

    /**
     * 验证菜单参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        //上级菜单类型
        int parentType = MenuConstant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = getMenuById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
//        if (menu.getType() == MenuConstant.MenuType.CATALOG.getValue() ||
//                menu.getType() == MenuConstant.MenuType.MENU.getValue()) {
//            if (parentType != MenuConstant.MenuType.CATALOG.getValue()) {
//                throw new BaseException("上级菜单只能为目录类型");
//            }
//            return;
//        }
        //按钮
        if (menu.getType() == MenuConstant.MenuType.BUTTON.getValue()) {
            if (parentType != MenuConstant.MenuType.MENU.getValue()) {
                throw new BaseException("上级菜单只能为菜单类型");
            }
        }
    }


    // TODO 优化
    @Override
    public List<MenuTreeVo> menuTree() {
        LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper = Wrappers.<SysMenu>query().lambda();
        sysMenuLambdaQueryWrapper.select(SysMenu::getMenuId, SysMenu::getName, SysMenu::getParentId, SysMenu::getType, SysMenu::getSort);
        List<SysMenu> sysMenus = new ArrayList<>();
        List<MenuTreeVo> menuTreeVoList = new ArrayList<>();
        List<SysMenu> menus = baseMapper.selectList(sysMenuLambdaQueryWrapper);

        menus.forEach(menu -> {
            MenuTreeVo menuTreeVo = new MenuTreeVo();
            BeanUtil.copyProperties(menu, menuTreeVo);

            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menuTreeVo.setLevel(0);
                menuTreeVo.setTitle(menu.getName());
                menuTreeVo.setKey(menu.getMenuId());
                menuTreeVo.setValue(String.valueOf(menu.getMenuId()));
                if (MmcUtil.exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                    menuTreeVoList.add(menuTreeVo);
                }
            }
        });
        menuTreeVoList.sort(Comparator.comparing(MenuTreeVo::getSort));
        MmcUtil.findChildren1(menuTreeVoList, menus, 0);
        return menuTreeVoList;
    }

}
