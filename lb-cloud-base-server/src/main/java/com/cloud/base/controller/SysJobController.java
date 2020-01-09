package com.cloud.base.controller;


import com.cloud.base.entity.SysJob;
import com.cloud.base.service.ISysJobService;
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
 * 岗位管理 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-05-01
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "岗位模块")
@RestController
@RequestMapping("/job")
public class SysJobController {

    @Autowired
    private ISysJobService jobService;


    /**
     * 获取岗位列表
     *
     * @param page
     * @param pageSize
     * @param jobName
     * @return
     */
    @ApiOperation("获取岗位列表")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:job:view')")
    public R getList(Integer page, Integer pageSize, @RequestParam(defaultValue = "") String jobName) {
        return R.ok(jobService.selectJobList(page, pageSize, jobName));
    }

    /**
     * 保存岗位
     *
     * @param sysJob
     * @return
     */
    @ApiOperation("添加岗位")
    @SysOperateLog(descrption = "添加岗位")
    @PreAuthorize("hasAuthority('sys:job:add')")
    @PostMapping
    public R add(@RequestBody SysJob sysJob) {
        return R.ok(jobService.save(sysJob));
    }

    /**
     * 根据id删除岗位
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除岗位")
    @SysOperateLog(descrption = "根据id删除岗位")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:job:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(jobService.removeById(id));
    }


    /**
     * 批量删除岗位
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量删除岗位")
    @SysOperateLog(descrption = "批量删除岗位")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/batchDelete")
    public R deleteBatch(@RequestParam(name = "ids") String ids) {
        List<Integer> listIds = Arrays.stream(ids.split(",")).map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        return R.ok(jobService.batchDeleteJobByIds(listIds));
    }

    /**
     * 更新岗位
     *
     * @param sysJob
     * @return
     */
    @ApiOperation("查询字典详情集合")
    @SysOperateLog(descrption = "更新岗位")
    @PreAuthorize("hasAuthority('sys:job:update')")
    @PutMapping
    public R update(@RequestBody SysJob sysJob) {
        return R.ok(jobService.updateById(sysJob));
    }


    /**
     * 根据部门id查询所属下的岗位信息
     *
     * @param deptId
     * @return
     */
    @GetMapping("/{id}")
    public R selectJobListByDeptId(@PathVariable("id") Integer deptId) {
        return R.ok(jobService.selectJobListByDeptId(deptId));
    }


}

