package com.real.shopping.utils;

import com.real.shopping.dataobject.UserDO;
import com.real.shopping.dataobject.UserPasswordDO;
import com.real.shopping.dataobject.UserRoleDO;
import com.real.shopping.service.model.UserModel;
import com.real.shopping.service.model.UserRoleModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mabin
 * @create: 2019/4/9 11:47
 */
@Component
public class CommonServiceUtil {
    public UserPasswordDO convertPasswordDOFromModel(UserModel userModel) {
        if (userModel==null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        BeanUtils.copyProperties(userModel,userPasswordDO);
        return userPasswordDO;
    }



    public UserDO convertDOfromModel(UserModel userModel) {
        if (userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    public UserModel convertModelFromDataObject(UserDO userDO,UserPasswordDO userPasswordDO){
        if (userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        return userModel;
    }

    public List<UserRoleModel> convertRoleModelFromRoleDO(List<UserRoleDO> userRoleDOList){
        List<UserRoleModel> userRoleModelList = userRoleDOList.stream().map(userRoleDO -> {
            UserRoleModel userRoleModel = new UserRoleModel();
            BeanUtils.copyProperties(userRoleDO,userRoleModel);
            return userRoleModel;
        }).collect(Collectors.toList());
        return userRoleModelList;
    }
}
