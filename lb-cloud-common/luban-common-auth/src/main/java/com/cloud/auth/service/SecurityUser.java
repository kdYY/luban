package com.cloud.auth.service;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @ClassName SecurityUser
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@Setter
@Getter
@Accessors(chain = true)
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 1L;


    private Integer userId;
    private String username;
    private String password;
    private String identificationNumber;
    private Collection<? extends GrantedAuthority> authorities;


    /**
     * @param userId               用户Id
     * @param username             用户名称
     * @param password             密码
     * @param identificationNumber 证件号
     * @param authorities          权限
     */
    public SecurityUser(Integer userId, String username, String password, String identificationNumber, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.identificationNumber = identificationNumber;
        this.authorities = authorities;

    }

    /**
     * 返回分配给用户的角色列表
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 账户是否未过期,过期无法验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
