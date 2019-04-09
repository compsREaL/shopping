package com.real.shopping.service.model;

import java.io.Serializable;

/**
 * @author: mabin
 * @create: 2019/4/9 10:39
 */
public class UserRoleModel implements Serializable {

    private Integer id;

    private Integer userId;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
