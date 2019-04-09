package com.real.shopping.service;

import com.real.shopping.service.model.UserRoleModel;

import java.util.List;

/**
 * @author: mabin
 * @create: 2019/4/8 15:03
 */
public interface UserRoleService {

    List<UserRoleModel> getRolesByUserId(Integer userId);
}
