package com.cloud.auth.security;

import cn.hutool.core.util.ObjectUtil;
import com.cloud.auth.feign.RemoteUserService;
import com.cloud.base.dto.UserDetailsInfo;
import com.cloud.base.entity.SysUser;
import com.cloud.auth.service.SecurityUser;
import com.cloud.core.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R<UserDetailsInfo> info = remoteUserService.info(username);

        if (ObjectUtil.isNull(info) || info.getCode() == HttpStatus.NOT_FOUND.value()) {
            log.debug("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        UserDetailsInfo userDetailsInfo = info.getData();
        SysUser user = userDetailsInfo.getSysUser();
        Set<String> permissions = userDetailsInfo.getPermissions();
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
        return new SecurityUser(user.getUserId(), username, "{bcrypt}" + user.getPassword(), user.getIdentificationNumber() == null ? null : user.getIdentificationNumber(), authorities);

    }

    /**
     * 手机验证码登录
     *
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        //  通过手机号mobile去数据库里查找用户以及用户权限
        R<UserDetailsInfo> info = remoteUserService.info(mobile);
        if (info.getCode() == HttpStatus.NOT_FOUND.value()) {
            log.info("登录手机号：" + mobile + " 不存在.");
            throw new UsernameNotFoundException("登录手机号：" + mobile + " 不存在");
        }
        UserDetailsInfo userDetailsInfo = info.getData();
        SysUser user = userDetailsInfo.getSysUser();
        Set<String> permissions = userDetailsInfo.getPermissions();
        // 获取用户拥有的角色
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
        return new SecurityUser(user.getUserId(), user.getUsername(), "{bcrypt}" + user.getPassword(), user.getIdentificationNumber() == null ? null : user.getIdentificationNumber(), authorities);
    }
}
