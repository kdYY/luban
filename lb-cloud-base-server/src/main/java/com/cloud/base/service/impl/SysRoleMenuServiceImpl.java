package com.cloud.base.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.entity.SysRoleMenu;
import com.cloud.base.mapper.SysRoleMenuMapper;
import com.cloud.base.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public List<Integer> getMenuIdByUserId(Integer userId) {
        return baseMapper.getMenuIdByUserId(userId);
    }

    @Override
    public List<Integer> getMenuIdByRoleId(Integer roleId) {
        List<SysRoleMenu> sysRoleMenus = baseMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().select(SysRoleMenu::getMenuId).eq(SysRoleMenu::getRoleId, roleId));
        return sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }
}
