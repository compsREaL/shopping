package com.real.shopping.service.impl;

import com.real.shopping.dao.UserDOMapper;
import com.real.shopping.dao.UserPasswordDOMapper;
import com.real.shopping.dao.UserRoleDOMapper;
import com.real.shopping.dataobject.UserDO;
import com.real.shopping.dataobject.UserPasswordDO;
import com.real.shopping.dataobject.UserRoleDO;
import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.service.UserService;
import com.real.shopping.service.model.UserModel;
import com.real.shopping.service.model.UserRoleModel;
import com.real.shopping.utils.CommonServiceUtil;
import com.real.shopping.validator.ValidatorImpl;
import com.real.shopping.validator.ValidatorResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mabin
 * @create: 2019/4/4 17:06
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private UserRoleDOMapper userRoleDOMapper;

    @Autowired
    private CommonServiceUtil serviceUtil;

    @Override
    public UserModel getUserById(Integer id) {
        //通过传入的id从数据库中获取对应的UserDO
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        //根据用户id查询用户密码
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        return serviceUtil.convertModelFromDataObject(userDO,userPasswordDO);
    }

    /**
     * @description 由于同时涉及到user_info表和user_password表两张表的操作，需要使用Transactional注解管理事务
     * @param userModel
     * @throws BussinessException
     */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BussinessException {
        if(userModel == null){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR);
        }

        ValidatorResult validatorResult = validator.validate(userModel);
        if (validatorResult.isHasErrors()){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,validatorResult.getErrMsg());
        }

        UserDO userDO = serviceUtil.convertDOfromModel(userModel);
        try{
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"该手机号已被注册，请使用新的手机号码进行注册！");
        } catch (Exception e){
            e.getMessage();
        }

        UserPasswordDO userPasswordDO = serviceUtil.convertPasswordDOFromModel(userModel);
        userPasswordDO.setUserId(userDO.getId());
        userPasswordDOMapper.insertSelective(userPasswordDO);

        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(userDO.getId());
        userRoleDOMapper.insertSelective(userRoleDO);
        return;
    }

    @Override
    public UserModel validateLogin(String telephone, String encrptPassword) throws BussinessException {

        UserModel userModel = getByTelephone(telephone);
        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    @Cacheable(value = "getByTelephone",key = "#telephone",unless = "#result==null ")
    public UserModel getByTelephone(String telephone) throws BussinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null){
            throw new BussinessException(EmBussinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = serviceUtil.convertModelFromDataObject(userDO,userPasswordDO);
        List<UserRoleDO> rolesDO = userRoleDOMapper.getRolesByUserId(userModel.getId());
        List<UserRoleModel> rolesModel = rolesDO.stream().map(userRoleDO -> {
            UserRoleModel userRoleModel = new UserRoleModel();
            BeanUtils.copyProperties(userRoleDO,userRoleModel);
            return userRoleModel;
        }).collect(Collectors.toList());
        userModel.setRoleModelList(rolesModel);
        return userModel;
    }


}
