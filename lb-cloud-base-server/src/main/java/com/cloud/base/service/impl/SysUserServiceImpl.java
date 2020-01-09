package com.cloud.base.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.dto.RepeatCheckDTO;
import com.cloud.base.dto.UserDTO;
import com.cloud.base.dto.UserDetailsInfo;
import com.cloud.base.entity.SysDictItem;
import com.cloud.base.entity.SysUser;
import com.cloud.base.entity.SysUserRole;
import com.cloud.base.mapper.SysUserMapper;
import com.cloud.base.service.*;
import com.cloud.core.exception.BaseException;
import com.cloud.core.mybatis.DataScope;
import com.cloud.core.utils.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @Author kevins
 * @since 2019-04-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDictItemService dictItemService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysRoleService roleService;

    @Override
    public IPage<SysUser> getUsersWithRolePage(Page page, UserDTO userDTO) {

//        if (ObjectUtil.isNotNull(userDTO) && userDTO.getDeptId() != 0) {
//            userDTO.setDeptList(deptService.selectDeptIds(userDTO.getDeptId()));
//        }
        IPage<SysUser> userList = baseMapper.getUserVosPage(page, userDTO, new DataScope());
        userList.getRecords().forEach(user -> user.setRoleList(roleService.findRolesByUserId(user.getUserId())));
        return userList;
    }

    @Override
    public List<SysUser> getUsersWithRole(UserDTO userDTO) {
        List<SysUser> userList = baseMapper.getUserVos(userDTO, new DataScope());
        userList.forEach(user -> user.setTypeName(dictItemService.getOne(Wrappers.<SysDictItem>lambdaQuery().select(SysDictItem::getItemValue).eq(SysDictItem::getId, user.getType()).eq(SysDictItem::getStatus, 1)).getItemValue()));
        return userList;
    }

    @Override
    public List<SysUser> getUsersByDeptIds(UserDTO userDTO) {
        Set<Integer> ids = new HashSet<Integer>();
        if (ObjectUtil.isNotNull(userDTO) && ObjectUtil.isNotNull(userDTO.getDeptList())) {
            for (Integer deptId : userDTO.getDeptList()) {
                ids.addAll(deptService.selectDeptIds(deptId));
            }
        }
        userDTO.setDeptIdsList(ids);
        return getUsersWithRole(userDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(UserDTO userDto) throws Exception {
        //RSA验证
        if (!RSAUtil.userVerifySign()) {
            throw new BaseException("签名错误");
        }
        int topLimit = RSAUtil.userDecode();
        int count = this.count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getDelFlag, 0));
        //系统容量大于等于最低限制
        if (count >= topLimit) {
            throw new BaseException("人员数量已到达上限");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        baseMapper.insert(sysUser);

        if (userDto.getRoleList() != null && userDto.getRoleList().size() > 0) {
            List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(item);
                sysUserRole.setUserId(sysUser.getUserId());
                return sysUserRole;
            }).collect(Collectors.toList());
            userRoleService.saveBatch(userRoles);
        }
        return true;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        baseMapper.updateById(sysUser);
        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, sysUser.getUserId()));
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());

        return userRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeUser(Integer userId) {
        baseMapper.updateById(new SysUser().setUserId(userId).setDelFlag("1"));
        return userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public boolean restPass(Integer userId, String password) {
        return this.updateById(new SysUser().setPassword(password).setUserId(userId));
    }

    @Override
    public UserDetailsInfo findUserInfo(SysUser sysUser) {
        UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
        // 权限集合
        Set<String> permissions = findPermsByUserId(sysUser.getUserId());
        // 角色集合
        Set<String> roleIds = findRoleIdByUserId(sysUser.getUserId());
        permissions.addAll(roleIds);
        userDetailsInfo.setSysUser(sysUser);
        userDetailsInfo.setPermissions(permissions);
        return userDetailsInfo;
    }

    @Override
    public SysUser findUserInByName(String username) {
        return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPhone, SysUser::getEmail, SysUser::getPassword, SysUser::getDeptId, SysUser::getJobId, SysUser::getAvatar)
                .eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return menuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(UserDTO userDTO) {
//        // 查询用户名是否存在
//        SysUser byUserInfoName = findUserByUserIdOrUserNameOrPhone(userDTO.getUsername());
//        if (ObjectUtil.isNotNull(byUserInfoName)) {
//            throw new BaseException("账户名已被注册");
//        }
//        SysUser securityUser = findUserByUserIdOrUserNameOrPhone(userDTO.getPhone());
//        if (ObjectUtil.isNotNull(securityUser)) {
//            throw new BaseException("手机号已被注册");
//        }
//        userDTO.setDeptId(6);
//        userDTO.setJobId(4);
//        userDTO.setLockFlag("0");
//        SysUser sysUser = new SysUser();
//        // 对象拷贝
//        BeanUtil.copyProperties(userDTO, sysUser);
//        // 加密后的密码
////        sysUser.setPassword(PreUtil.encode(userDTO.getPassword()));
//        baseMapper.insertUser(sysUser);
//        SysUserRole sysUserRole = new SysUserRole();
//        sysUserRole.setRoleId(14);
//        sysUserRole.setUserId(sysUser.getUserId());
//        return userRoleService.save(sysUserRole);
        return false;
    }

    @Override
    public boolean updateUserInfo(SysUser sysUser) {
        return baseMapper.updateById(sysUser) > 0;
    }

    @Override
    public SysUser findUserByUserIdOrUserNameOrPhone(String userIdOrUserNameOrPhone) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword)
                .eq(SysUser::getUsername, userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getPhone, userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getUserId, userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getEmail, userIdOrUserNameOrPhone);
        return baseMapper.selectOne(select);
    }

    @Override
    public boolean repeatCheck(RepeatCheckDTO repeatCheckDTO) {

        String fieldVal = repeatCheckDTO.getFieldVal();
        Integer dataId = repeatCheckDTO.getDataId();
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, fieldVal)
                .or()
                .eq(SysUser::getPhone, fieldVal)
                .or()
                .eq(SysUser::getEmail, fieldVal);
        // 不为空说明是编辑情况
        if (ObjectUtil.isNotNull(dataId)) {
            lambdaQueryWrapper.eq(SysUser::getUserId, dataId);
            return baseMapper.selectCount(lambdaQueryWrapper) < 0;
        }
        return baseMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public SysUser getUserBySocial(String providerId, int providerUserId) {
        return baseMapper.getUserBySocial(providerId, providerUserId);
    }

//    @Override
//    public SysUser findSecurityUserByUser(SysUser sysUser) {
//        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
//                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword);
//        if (StrUtil.isNotEmpty(sysUser.getUsername())) {
//            select.eq(SysUser::getUsername, sysUser.getUsername());
//        } else if (StrUtil.isNotEmpty(sysUser.getPhone())) {
//            select.eq(SysUser::getPhone, sysUser.getPhone());
//        } else if (ObjectUtil.isNotNull(sysUser.getUserId()) && sysUser.getUserId() != 0) {
//            select.eq(SysUser::getUserId, sysUser.getUserId());
//        }
//
//
//        return baseMapper.selectOne(select);
//    }

//    @Override
//    public boolean doPostSignUp(SysUser user) {
    // 进行账号校验
//        SysUser sysUser = findSecurityUserByUser(new SysUser().setUsername(user.getUsername()));
//        if (ObjectUtil.isNull(sysUser)) {
//            throw new BaseException("账号不存在");
//        }
//        Integer userId = sysUser.getUserId();
//        try {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername()去验证用户名和密码，
//            // 如果正确，则存储该用户名密码到security 的 context中
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        } catch (Exception e) {
//            if (e instanceof BadCredentialsException) {
//                throw new BaseException("用户名或密码错误", 402);
//            } else if (e instanceof DisabledException) {
//                throw new BaseException("账户被禁用", 402);
//            } else if (e instanceof AccountExpiredException) {
//                throw new BaseException("账户过期无法验证", 402);
//            } else {
//                throw new BaseException("账户被锁定,无法登录", 402);
//            }
//        }
//        //将业务系统的用户与社交用户绑定
//        socialRedisHelper.doPostSignUp(user.getKey(), userId);
//        return true;
//    }


}
