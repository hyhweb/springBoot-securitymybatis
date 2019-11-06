package com.springboot.securitymybatis.entity;

import java.io.Serializable;

/**
 * (TSysUserRole)实体类
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
public class TSysUserRole implements Serializable {
    private static final long serialVersionUID = -36724821536791790L;
    //ID
    private Long userRoleId;
    //user_id
    private Long userId;
    //role_id
    private Long roleId;


    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}