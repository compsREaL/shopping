package com.real.shopping.service;

import com.real.shopping.error.BussinessException;
import com.real.shopping.service.model.UserModel;

/**
 * @author: mabin
 * @create: 2019/4/4 16:55
 */
public interface UserService {

    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BussinessException;

    UserModel validateLogin(String telephone, String encrptPassword) throws BussinessException;

    UserModel getByTelephone(String telephone) throws BussinessException;
}
