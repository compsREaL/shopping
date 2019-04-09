package com.real.shopping.controller;

import com.alibaba.druid.util.StringUtils;
import com.real.shopping.controller.vo.UserVO;
import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.response.CommonReturnType;
import com.real.shopping.service.UserService;
import com.real.shopping.service.model.UserModel;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author: mabin
 * @create: 2019/4/4 16:55
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",origins={"*"})
public class UserController extends BaseController{

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getUser() throws BussinessException {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()){
            throw new BussinessException(EmBussinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) request.getSession().getAttribute("LOGIN_USER");
        return CommonReturnType.create(userModel);
    }


    @RequestMapping(value = "/otp",method = RequestMethod.POST,consumes = CONTENT_TYPE)
    @ResponseBody
    public CommonReturnType getotp(@RequestParam(name = "telephone") String telephone){
        //生成六位数随机验证码
        Random random = new Random();
        int randomInt = random.nextInt(899999);
        randomInt = randomInt + 100000;
        String otpCode = String.valueOf(randomInt);
        request.getSession().setAttribute(telephone,otpCode);
        System.out.println("telephone="+telephone+"&otpCode="+otpCode);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = CONTENT_TYPE)
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,@RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "password") String password,@RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,@RequestParam(name = "name")String name) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String sessionOtpCode = (String) request.getSession().getAttribute(telephone);
        if (!StringUtils.equals(sessionOtpCode,otpCode)){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"验证码不正确");
        }
        //注册用户
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setTelephone(telephone);
        userModel.setEncrptPassword(encodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/loginAjax",method = RequestMethod.POST,consumes = CONTENT_TYPE)
    @ResponseBody
    public CommonReturnType lgAjax(@RequestParam(name = "telephone") String telephone,
                                   @RequestParam(name = "password") String password)
            throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if ( org.apache.commons.lang3.StringUtils.isEmpty(telephone) || org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"请填写正确的手机号或密码");
        }
        UserModel userModel = userService.validateLogin(telephone,encodeByMD5(password));
        //将登录凭证添加到用户登录成功的session中
        this.request.getSession().setAttribute("IS_LOGIN",true);
        this.request.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder encoder = new BASE64Encoder();
        String password = encoder.encode(md5.digest(str.getBytes("utf-8")));
        return password;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BussinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertVOFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertVOFromModel(UserModel userModel) {
        if (userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

}
