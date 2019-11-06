package com.springboot.securitymybatis.entity;

import java.io.Serializable;

/**
 * (TSysRole)实体类
 *
 * @author makejava
 * @since 2019-11-05 11:37:31
 */
public class TSysRole implements Serializable {
    private static final long serialVersionUID = -23655916518367324L;
    
    private Long roleId;
    
    private String name;
    
    private String nameText;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

}