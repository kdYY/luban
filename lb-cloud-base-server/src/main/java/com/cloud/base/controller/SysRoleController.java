package com.cloud.base.controller;


import com.cloud.base.dto.RoleDTO;
import com.cloud.base.service.ISysRoleMenuService;
import com.cloud.base.service.ISysRoleService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "角色模块")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Resource
    private ISysRoleService roleService;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    /**
     * 获取角色列表
     *
     * @return
     */
    @ApiOperation("获取角色列表")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:role:view')")
    public R getRoleList(@RequestParam String roleName) {
        return R.ok(roleService.selectRoleList(roleName));
    }

    /**
     * 保存角色
     *
     * @param roleDto
     * @return
     */
    @ApiOperation("添加角色")
    @SysOperateLog(descrption = "添加角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @PostMapping
    public R add(@RequestBody RoleDTO roleDto) {
        return R.ok(roleService.saveRole(roleDto));
    }

    /**
     * 根据角色id获取菜单
     *
     * @param roleId
     * @return
     */
    @ApiOperation("据角色id获取菜单")
    @SysOperateLog(descrption = "据角色id获取菜单")
    @GetMapping("/queryRolePermission/{roleId}")
    public R getRoleMenus(@PathVariable("roleId") Integer roleId) {
        return R.ok(roleMenuService.getMenuIdByRoleId(roleId));
    }

    /**
     * 更新角色
     *
     * @param roleDto
     * @return
     */
    @ApiOperation("更新角色")
    @SysOperateLog(descrption = "更新角色")
    @PreAuthorize("hasAuthority('sys:role:update')")
    @PutMapping
    public R update(@RequestBody RoleDTO roleDto) {
        return R.ok(roleService.updateRole(roleDto));
    }


    /**
     * 更新角色权限
     *
     * @param roleDto
     * @return
     */
    @ApiOperation("更新角色权限")
    @SysOperateLog(descrption = "更新角色权限")
    @PreAuthorize("hasAuthority('sys:role:update')")
    @PutMapping("/updateRolePermission")
    public R updateRolePermission(@RequestBody RoleDTO roleDto) {
        return R.ok(roleService.updateRolePermission(roleDto));
    }

    /**
     * 删除角色以及权限
     *
     * @param roleId
     * @return
     */
    @ApiOperation("删除角色以及权限")
    @SysOperateLog(descrption = "删除角色以及权限")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/{roleId}")
    public R delete(@PathVariable("roleId") Integer roleId) {
        return R.ok(roleService.removeById(roleId));
    }


    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量删除角色")
    @SysOperateLog(descrption = "批量删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/batchDelete")
    public R deleteBatch(@RequestParam(name = "ids") String ids) {
        List<Integer> listIds = Arrays.stream(ids.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        return R.ok(roleService.batchDeleteRoleByIds(listIds));
    }
}

