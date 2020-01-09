package com.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.dto.MenuDTO;
import com.cloud.base.entity.SysMenu;
import com.cloud.base.vo.MenuTreeVo;
import com.cloud.core.utils.R;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @since 2019-04-21
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 保存菜单信息
     *
     * @param entity
     * @return
     */
    @Override
    boolean save(SysMenu entity);

    /**
     * 更新菜单信息
     *
     * @param entity
     * @return
     */
    boolean updateMenuById(MenuDTO entity);

    /**
     * 删除菜单信息
     *
     * @param id
     * @return
     */
    R removeMenuById(Serializable id);

    /**
     * 根据用户id查找菜单树
     *
     * @return
     */
    List<SysMenu> selectMenuTree(Integer uid);

    /**
     * @Description 根据父id查询菜单
     * @Date 18:43 2019-05-12
     **/
    SysMenu getMenuById(Integer parentId);

    /**
     * @Description 根据用户id查询权限
     **/
    List<String> findPermsByUserId(Integer userId);

    List<MenuTreeVo> menuTree();
}
