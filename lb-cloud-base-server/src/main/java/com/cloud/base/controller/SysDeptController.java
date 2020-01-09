package com.cloud.base.controller;


import com.cloud.base.dto.DeptDTO;
import com.cloud.base.entity.SysDept;
import com.cloud.base.service.ISysDeptService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/dept")
@Api(description = "部门模块")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SysDeptController {

    @Autowired
    private ISysDeptService deptService;

    /**
     * 保存部门信息
     *
     * @param sysDept
     * @return
     */
    @ApiOperation("添加部门信息")
    @SysOperateLog(descrption = "添加部门信息")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:dept:add')")
    public R add(@RequestBody SysDept sysDept) {
        return R.ok(deptService.save(sysDept));
    }

    /**
     * 获取部门信息
     *
     * @return
     */
    @ApiOperation("获取部门信息")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptList() {
        return R.ok(deptService.selectDeptList());
    }


    /**
     * 获取部门树
     *
     * @return
     */
    @ApiOperation("获取部门树")
    @GetMapping("/queryDepartTreeList")
    public R queryDepartTreeList() {
        return R.ok(deptService.queryDepartTreeList());
    }


    /**
     * 更新部门信息
     *
     * @return
     */
    @ApiOperation("更新部门信息")
    @SysOperateLog(descrption = "更新部门信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:dept:update')")
    public R update(@RequestBody DeptDTO deptDto) {
        return R.ok(deptService.updateDeptById(deptDto));
    }

    /**
     * 根据id删除部门信息
     *
     * @return
     */
    @ApiOperation("根据id删除部门信息")
    @SysOperateLog(descrption = "根据id删除部门信息")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(deptService.removeById(id));
    }

    /**
     * 根据ids批量删除部门信息
     *
     * @param ids
     * @return
     */
    @ApiOperation("根据ids批量删除部门信息")
    @SysOperateLog(descrption = "根据ids批量删除部门信息")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @DeleteMapping("/batchDelete")
    public R deleteBatch(@RequestParam(name = "ids") String ids) {
        List<Integer> listIds = Arrays.stream(ids.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        return R.ok(deptService.batchDeleteDeptByIds(listIds));
    }


}

