package com.real.shopping.controller;

import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mabin
 * @create: 2019/4/6 14:55
 */
public class BaseController {

    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    //定义exceptionHandler解决未被controller处理的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request,Exception ex){
        Map<String,Object> responseData = new HashMap<>();

        if (ex instanceof BussinessException){
            BussinessException bussinessException= (BussinessException) ex;
            responseData.put("errCode",bussinessException.getErrCode());
            responseData.put("errMsg",bussinessException.getErrMsg());
        }else {
            responseData.put("errCode", EmBussinessError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg",EmBussinessError.UNKNOW_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
