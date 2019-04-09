package com.real.shopping.service.impl;

import com.real.shopping.dao.UserRoleDOMapper;
import com.real.shopping.dataobject.UserRoleDO;
import com.real.shopping.service.UserRoleService;
import com.real.shopping.service.model.UserRoleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mabin
 * @create: 2019/4/8 15:05
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDOMapper userRoleDOMapper;

    @Override
    public List<UserRoleModel> getRolesByUserId(Integer userId) {
        List<UserRoleDO> userRoleDOList = userRoleDOMapper.getRolesByUserId(userId);
        List<UserRoleModel> userRoleModelList = userRoleDOList.stream().map(userRoleDO -> {
            UserRoleModel userRoleModel = new UserRoleModel();
            BeanUtils.copyProperties(userRoleDO,userRoleModel);
            return userRoleModel;
        }).collect(Collectors.toList());
        return userRoleModelList;
    }
}
