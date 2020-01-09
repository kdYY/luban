package com.cloud.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.dto.RoleDTO;
import com.cloud.base.entity.SysRole;
import com.cloud.base.entity.SysRoleMenu;
import com.cloud.base.mapper.SysRoleMapper;
import com.cloud.base.service.ISysRoleMenuService;
import com.cloud.base.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private ISysRoleMenuService roleMenuService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRole(RoleDTO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        // 根据数据权限范围查询部门ids
//        List<Integer> ids = dataScopeContext.getDeptIdsForDataScope(roleDto, roleDto.getDsType());
//        StringJoiner dsScope = new StringJoiner(",");
//        ids.forEach(integer -> {
//            dsScope.add(Integer.toString(integer));
//        });
//        sysRole.setDsScope(dsScope.toString());
        baseMapper.insertRole(sysRole);

//        Integer roleId = sysRole.getRoleId();
        // 维护角色部门权限
        // 根据数据权限范围查询部门ids
//        if (CollectionUtil.isNotEmpty(ids)) {
//            List<SysRoleDept> roleDepts = ids.stream().map(integer -> {
//                SysRoleDept sysRoleDept = new SysRoleDept();
//                sysRoleDept.setDeptId(integer);
//                sysRoleDept.setRoleId(roleId);
//                return sysRoleDept;
//            }).collect(Collectors.toList());
//
//            roleDeptService.saveBatch(roleDepts);
//        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(RoleDTO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
//        updateById(sysRole);
//        roleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, sysRole.getRoleId()));
        // 根据数据权限范围查询部门ids
//        List<Integer> ids = dataScopeContext.getDeptIdsForDataScope(roleDto, roleDto.getDsType());
//        StringJoiner dsScope = new StringJoiner(",");
//        ids.forEach(integer -> {
//            dsScope.add(Integer.toString(integer));
//        });
//        if (CollectionUtil.isNotEmpty(ids)) {
//            List<SysRoleDept> roleDepts = ids.stream().map(integer -> {
//                SysRoleDept sysRoleDept = new SysRoleDept();
//                sysRoleDept.setDeptId(integer);
//                sysRoleDept.setRoleId(roleDto.getRoleId());
//                return sysRoleDept;
//            }).collect(Collectors.toList());
//            roleDeptService.saveBatch(roleDepts);
//        }
//        sysRole.setDsScope(dsScope.toString());
        baseMapper.updateById(sysRole);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        roleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, id));
//        roleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, id));
        return super.updateById(new SysRole().setRoleId((int) id).setDelFlag("1"));
    }

    @Override
    public List<SysRole> selectRoleList(String roleName) {
        LambdaQueryWrapper<SysRole> sysRoleLambdaQueryWrapper = Wrappers.<SysRole>lambdaQuery();
        if (StrUtil.isNotEmpty(roleName)) {
            sysRoleLambdaQueryWrapper.like(SysRole::getRoleName, roleName);
        }
        return baseMapper.selectList(sysRoleLambdaQueryWrapper);
//        return sysRoles.stream().peek(sysRole ->
//                sysRole.setRoleDepts(roleDeptService.getRoleDeptIds(sysRole.getRoleId()).stream().map(SysRoleDept::getDeptId).collect(Collectors.toList()))
//        ).collect(Collectors.toList());
    }

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }

    @Override
    public boolean batchDeleteRoleByIds(List<Integer> ids) {
        return this.removeByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRolePermission(RoleDTO roleDto) {
        //1.删除之前的角色
        //2.新增权限
        roleMenuService.remove(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, roleDto.getRoleId()));
        List<SysRoleMenu> collect = roleDto.getPermissionIds().stream().map(item -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(item);
            sysRoleMenu.setRoleId(roleDto.getRoleId());
            return sysRoleMenu;
        }).collect(Collectors.toList());
        return roleMenuService.saveBatch(collect);
    }

}
