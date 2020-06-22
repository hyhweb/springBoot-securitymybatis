package com.springboot.securitymybatis.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * (TSysUser)实体类
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
public class TSysUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 201447118197141522L;
    // 用户ID
    private Long id;
    // 用户名
    private String username;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private List<TSysRole> roles;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TSysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TSysRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (TSysRole sysRole : roles) {
            authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
        }

        return authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
