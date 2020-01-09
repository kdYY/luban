package com.cloud.base.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.dto.DictDTO;
import com.cloud.base.entity.SysDict;
import com.cloud.base.service.ISysDictService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-05-17
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "字典模块")
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private ISysDictService dictService;

    /**
     * 添加字典信息
     *
     * @param sysDict
     * @return
     */
    @ApiOperation("添加字典信息")
    @SysOperateLog(descrption = "添加字典信息")
    @PostMapping
    public R add(@RequestBody SysDict sysDict) {
        return R.ok(dictService.save(sysDict));
    }

    /**
     * 获取字典列表集合
     *
     * @param page
     * @param sysDict
     * @return
     */
    @ApiOperation("查询字典集合")
    @SysOperateLog(descrption = "查询字典集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dipt:view')")
    public R getList(Page page, SysDict sysDict) {
        return R.ok(dictService.page(page, Wrappers.query(sysDict)));
    }


    /**
     * 更新字典
     *
     * @param dictDto
     * @return
     */
    @ApiOperation("更新字典")
    @SysOperateLog(descrption = "更新字典")
    @PutMapping
    public R update(@RequestBody DictDTO dictDto) {
        return R.ok(dictService.updateDict(dictDto));
    }


    /**
     * 根据id删除字典
     *
     * @param id
     * @return //
     */
    @ApiOperation("根据id删除字典")
    @SysOperateLog(descrption = "根据id删除字典")
    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") int id) {
        return R.ok(dictService.removeById(id));
    }

}

