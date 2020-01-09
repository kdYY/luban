package com.cloud.base.controller;

import com.cloud.base.dto.RepeatCheckDTO;
import com.cloud.base.service.ISysUserService;
import com.cloud.core.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SysRepeatCheckController
 * @Description 重复校验
 * @Author kevins
 * @Date 2019-09-02 10:32
 * @Version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "数据校验模块")
@RestController
@RequestMapping("repeatCheck")
public class SysRepeatCheckController {

    @Autowired
    private ISysUserService userService;


    /**
     * 校验数据是否在系统中是否存在
     *
     * @return
     */
    @ApiOperation("校验数据是否在系统中是否存在")
    @GetMapping("/check")
    public R doDuplicateCheck(RepeatCheckDTO repeatCheckDTO) {
        return R.ok(userService.repeatCheck(repeatCheckDTO));
    }
}
