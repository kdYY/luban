package com.cloud.base.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.entity.SysLog;
import com.cloud.base.service.ISysLogService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-04-27
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "日志模块")
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Resource
    private ISysLogService sysLogService;

    /**
     * 分页查询日志列表
     *
     * @param page
     * @param sysLog
     * @return
     */
    @ApiOperation("分页查询日志列表")
    @GetMapping
    public R page(Page page, SysLog sysLog) {
        return R.ok(sysLogService.page(page, Wrappers.query(sysLog).lambda().orderByDesc(SysLog::getStartTime)));
    }


    /**
     * 保存日志信息
     *
     * @param sysLog
     * @return
     */
    @ApiOperation("保存日志信息")
    @PostMapping
    public R add(@RequestBody SysLog sysLog) {
        return R.ok(sysLogService.save(sysLog));
    }


    /**
     * 根据id删除日志
     *
     * @param logId
     * @return
     */
    @ApiOperation("根据id删除日志")
    @SysOperateLog(descrption = "根据id删除日志")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    @DeleteMapping("/{logId}")
    public R delete(@PathVariable("logId") Integer logId) {
        return R.ok(sysLogService.removeById(logId));
    }
}

