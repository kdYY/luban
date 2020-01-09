package com.cloud.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.entity.SysOauthClientDetails;
import com.cloud.base.service.SysOauthClientDetailsService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname OauthClientDetailsController
 * @Description TODO
 * @Author kevins
 * @Date 2019-09-05 11:43
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "客户端模块")
@RestController
@RequestMapping("/client")
public class SysOauthClientDetailsController {

    @Autowired
    private SysOauthClientDetailsService sysOauthClientDetailsService;

    /**
     * 分页查询客户端
     *
     * @param page
     * @param sysOauthClientDetails
     * @return
     */
    @ApiOperation("分页查询客户端")
    @GetMapping
    public R getOauthClientDetailsPage(Page page, SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
    }

    /**
     * 添加客户端
     *
     * @param sysOauthClientDetails
     * @return
     */
    @ApiOperation("添加客户端")
    @SysOperateLog(descrption = "添加客户端")
    @PreAuthorize("hasAuthority('sys:client:add')")
    @PostMapping
    public R add(@RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(sysOauthClientDetailsService.save(sysOauthClientDetails));
    }

    /**
     * 删除客户端
     *
     * @param id
     * @return
     */
    @ApiOperation("删除客户端")
    @SysOperateLog(descrption = "删除客户端")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:client:del')")
    public R removeById(@PathVariable String id) {
        return R.ok(sysOauthClientDetailsService.removeById(id));
    }

    /**
     * 编辑客户端
     *
     * @param sysOauthClientDetails
     * @return
     */
    @ApiOperation("编辑客户端")
    @SysOperateLog(descrption = "编辑客户端")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:client:edit')")
    public R update(@RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(sysOauthClientDetailsService.updateById(sysOauthClientDetails));
    }
}
