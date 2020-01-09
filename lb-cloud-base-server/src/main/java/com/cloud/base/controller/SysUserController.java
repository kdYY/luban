package com.cloud.base.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.base.dto.UserDTO;
import com.cloud.base.entity.SysUser;
import com.cloud.base.service.ISysUserService;
import com.cloud.core.utils.R;
import com.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "用户模块")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 保存用户包括角色和部门
     *
     * @param userDto
     * @return
     */
    @ApiOperation("保存用户包括角色和部门")
    @SysOperateLog(descrption = "保存用户包括角色和部门")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R add(@RequestBody UserDTO userDto) {
        try {
            return R.ok(userService.insertUser(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }


    /**
     * 获取用户列表集合
     *
     * @param page
     * @param userDTO
     * @return
     */
    @ApiOperation("查询用户集合")
    @SysOperateLog(descrption = "查询用户集合")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping
    public R getPageList(Page page, UserDTO userDTO) {
        return R.ok(userService.getUsersWithRolePage(page, userDTO));
    }

    /**
     * 获取用户列表集合，根据部门id递归查询
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("查询用户集合,根据部门id递归查询")
    @SysOperateLog(descrption = "查询用户集合,根据部门id递归查询")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping("/by_dept_ids")
    public R getPageList(UserDTO userDTO) {
        return R.ok(userService.getUsersByDeptIds(userDTO));
    }

    @ApiOperation("查询用户集合,不分页")
    @GetMapping("/list")
    public R getList(UserDTO userDTO) {
        return R.ok(userService.getUsersWithRole(userDTO));
    }

    /**
     * 更新用户包括角色和部门
     *
     * @param userDto
     * @return
     */
    @ApiOperation("更新用户包括角色和部门")
    @SysOperateLog(descrption = "更新用户包括角色和部门")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping
    public R update(@RequestBody UserDTO userDto) {
        return R.ok(userService.updateUser(userDto));
    }

    /**
     * 删除用户包括角色和部门
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据用户id删除用户包括角色和部门")
    @SysOperateLog(descrption = "根据用户id删除用户包括角色和部门")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{userId}")
    public R delete(@PathVariable("userId") Integer userId) {
        return R.ok(userService.removeUser(userId));
    }


    /**
     * 重置密码
     *
     * @param userDTO
     * @return
     */
    @ApiOperation("重置密码")
    @SysOperateLog(descrption = "重置密码")
    @PreAuthorize("hasAuthority('sys:user:rest')")
    @PutMapping("/restPass")
    public R restPass(@RequestBody UserDTO userDTO) {
        return R.ok(userService.restPass(userDTO.getUserId(), userDTO.getPassword()));
    }


    /**
     * 获取个人信息
     *
     * @return
     */
//    @ApiOperation("获取个人信息")
//    @GetMapping("/info")
//    public R getUserInfo() {
//        return R.ok(userService.findUserInByName(SecurityUtil.getUser().getUsername()));
//    }

    /**
     * 主要是提供给授权服务器使用
     *
     * @param username
     * @return
     */
    @ApiOperation("获取用户信息")
    @SysOperateLog(descrption = "获取用户信息")
    @GetMapping("/info/{username}")
    public R getInfo(@PathVariable String username) {
        SysUser user = userService.findUserByUserIdOrUserNameOrPhone(username);
        if (user == null) {
            return R.error(String.format(" %s", username));
        }
        return R.ok(userService.findUserInfo(user));
    }

    /**
     * 第三方登录调用
     *
     * @param providerId
     * @param providerUserId
     * @return
     */
    @ApiOperation("第三方登录调用获取用户信息")
    @SysOperateLog(descrption = "第三方登录调用获取用户信息")
    @GetMapping("/social/info")
    public R getUserInfoBySocial(@RequestParam String providerId, @RequestParam int providerUserId) {
        SysUser user = userService.getUserBySocial(providerId, providerUserId);
        if (user == null) {
            return R.error();
        }
        return R.ok(userService.findUserInfo(user));
    }


    /**
     * 修改密码
     *
     * @return
     */
//    @ApiOperation("修改密码")
//    @SysOperateLog(descrption = "修改密码")
//    @PreAuthorize("hasAuthority('sys:user:updatePass')")
//    @PutMapping("updatePass")
//    public R updatePass(@RequestBody SysUser sysUser) {
//         校验密码流程
//        SysUser user = userService.findUserByUserIdOrUserNameOrPhone(sysUser.getUsername());
//        if (!SecurityUtil.validatePass(sysUser.getPassword(), user.getPassword())) {
//            throw new BaseException("原密码错误");
//        }
//         修改密码流程
//        SysUser userForPass = new SysUser();
//        userForPass.setUserId(user.getUserId());
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        userForPass.setPassword(passwordEncoder.encode(sysUser.getPassword()));
//        return R.ok(userService.updateUserInfo(userForPass));
//    }

}

