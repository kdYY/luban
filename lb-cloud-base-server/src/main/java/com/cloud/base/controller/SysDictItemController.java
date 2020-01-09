package com.cloud.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.entity.SysDictItem;
import com.cloud.base.service.ISysDictItemService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname SysDictItemController
 * @Description
 * @Author
 * @Date 2019-09-02 18:14
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "字典详情模块")
@RestController
@RequestMapping("/dictItem")
public class SysDictItemController {


    @Autowired
    private ISysDictItemService dictItemService;

    /**
     * 分页查询字典详情内容
     *
     * @param page        分页对象
     * @param sysDictItem
     * @return
     */
    @ApiOperation("查询字典详情集合")
    @SysOperateLog(descrption = "查询字典详情集合")
    @GetMapping
    public R getDictItemPage(Page page, SysDictItem sysDictItem) {
        return R.ok(dictItemService.page(page, Wrappers.query(sysDictItem)));
    }

    /**
     * @param sysDictItem
     * @return
     */
    @ApiOperation("添加字典详情")
    @SysOperateLog(descrption = "添加字典详情")
    @PostMapping
    public R add(@RequestBody SysDictItem sysDictItem) {
        return R.ok(dictItemService.save(sysDictItem));
    }

    /**
     * @param sysDictItem
     * @return
     */
    @ApiOperation("更新字典详情")
    @SysOperateLog(descrption = "更新字典详情")
    @PutMapping
    public R update(@RequestBody SysDictItem sysDictItem) {
        return R.ok(dictItemService.updateById(sysDictItem));
    }

    /**
     * @param id
     * @return
     */
    @ApiOperation("删除字典详情")
    @SysOperateLog(descrption = "删除字典详情")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(dictItemService.updateById(new SysDictItem().setId(id).setStatus(0)));
    }


}
