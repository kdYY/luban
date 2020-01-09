package com.cloud.base.controller;


import com.alibaba.fastjson.JSONArray;
import com.cloud.base.dto.MenuDTO;
import com.cloud.base.entity.SysMenu;
import com.cloud.base.service.ISysMenuService;
import com.cloud.core.utils.MmcUtil;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "菜单模块")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @ApiOperation("添加菜单")
    @SysOperateLog(descrption = "添加菜单")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @PostMapping
    public R add(@RequestBody SysMenu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @ApiOperation("获取所有菜单")
    @GetMapping
    public R getMenuTree() {
//        PreSecurityUser securityUser = SecurityUtil.getUser();
        return R.ok(menuService.selectMenuTree(0));
    }


    @PreAuthorize("hasAuthority('sys:menu:update')")
    @GetMapping("/getMenuTree")
    public R getMenuTreeVos() {
        return R.ok(menuService.menuTree());
    }

    /**
     * 修改菜单
     *
     * @param menuDto
     * @return
     */
    @ApiOperation("修改菜单")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @SysOperateLog(descrption = "修改菜单")
    @PutMapping
    public R updateMenu(@RequestBody MenuDTO menuDto) {
        return R.ok(menuService.updateMenuById(menuDto));
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @ApiOperation("删除菜单")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @SysOperateLog(descrption = "删除菜单")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Integer id) {
        return menuService.removeMenuById(id);
    }

    /**
     * 获取菜单路由
     *
     * @return
     */
    @ApiOperation("获取菜单路由")
    @GetMapping("/getRouters")
    public R getRouters() {
        List<SysMenu> list = menuService.list();
        JSONArray menujsonArray = new JSONArray();
        MmcUtil.getPermissionJsonArray(menujsonArray, list, null);
        return R.ok(menujsonArray);
    }

}

