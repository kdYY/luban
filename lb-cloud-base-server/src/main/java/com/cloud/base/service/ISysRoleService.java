package com.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.dto.RoleDTO;
import com.cloud.base.entity.SysRole;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 保存角色和菜单
     *
     * @param roleDto
     * @return
     */
    boolean saveRole(RoleDTO roleDto);

    /**
     * 更新角色和菜单
     *
     * @param roleDto
     * @return
     */
    boolean updateRole(RoleDTO roleDto);

    /**
     * 根据主键删除角色
     *
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<SysRole> selectRoleList(String roleName);

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Integer userId);


    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    boolean batchDeleteRoleByIds(List<Integer> ids);


    boolean updateRolePermission(RoleDTO roleDto);
}
