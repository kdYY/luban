package com.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据用户id查询菜单集合
     *
     * @param userId
     * @return
     */
    List<Integer> getMenuIdByUserId(Integer userId);


    /**
     * 根据角色id查询菜单集合
     *
     * @param roleId
     * @return
     */
    List<Integer> getMenuIdByRoleId(Integer roleId);


}
