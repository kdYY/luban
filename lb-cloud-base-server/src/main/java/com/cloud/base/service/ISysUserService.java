package com.cloud.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.base.dto.RepeatCheckDTO;
import com.cloud.base.dto.UserDTO;
import com.cloud.base.dto.UserDetailsInfo;
import com.cloud.base.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param page    分页对象
     * @param userDTO 参数列表
     * @return
     */
    IPage<SysUser> getUsersWithRolePage(Page page, UserDTO userDTO);

    /**
     * @return java.util.List<com.mmc.base.entity.SysUser>
     * @Author kevins
     * @Description 查询用户信息（含有角色信息）
     * @Date 4:51 下午 2019/11/1
     * @Param [userDTO]
     **/
    List<SysUser> getUsersWithRole(UserDTO userDTO);

    /**
     * @return java.util.List<com.mmc.base.entity.SysUser>
     * @Author kevins
     * @Description 查询用户集合, 根据部门id递归查询
     * @Date 10:50 上午 2019/11/25
     * @Param [userDTO]
     **/
    List<SysUser> getUsersByDeptIds(UserDTO userDTO);

    /**
     * 保存用户以及角色部门等信息
     *
     * @param userDto
     * @return
     */
    boolean insertUser(UserDTO userDto) throws Exception;

    /**
     * 更新用户以及角色部门等信息
     *
     * @param userDto
     * @return
     */
    boolean updateUser(UserDTO userDto);

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    boolean removeUser(Integer userId);

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    boolean restPass(Integer userId, String password);

    /**
     * 获取授权用户信息
     *
     * @param sysUser
     * @return
     */
    UserDetailsInfo findUserInfo(SysUser sysUser);

    /**
     * 通过用户名查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findUserInByName(String username);

    /**
     * 根据用户id查询权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 通过用户id查询角色集合
     *
     * @param userId
     * @return
     */
    Set<String> findRoleIdByUserId(Integer userId);


    boolean register(UserDTO userDTO);

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    boolean updateUserInfo(SysUser sysUser);

    /**
     * 根据用户id / 用户名 / 手机号查询用户
     *
     * @param userIdOrUserNameOrPhone
     * @return
     */
    SysUser findUserByUserIdOrUserNameOrPhone(String userIdOrUserNameOrPhone);


    /**
     * 数据校验
     *
     * @param repeatCheckDTO
     * @return
     */
    boolean repeatCheck(RepeatCheckDTO repeatCheckDTO);

    /**
     * 根据社交类型和社交id查询社交用户信息
     *
     * @param providerId
     * @param providerUserId
     * @return
     */
    SysUser getUserBySocial(String providerId, int providerUserId);


}
