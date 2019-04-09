package com.real.shopping.error;

/**
 * @author: mabin
 * @create: 2019/4/4 16:24
 */
public enum  EmBussinessError implements CommonError {

    //通用错误类型
    PARAM_VALIDATION_ERRPR(10001,"参数不正确"),
    UNKNOW_ERROR(10002,"未知错误"),

    //20000开头错误类型为用户信息相关错误
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户手机号或密码错误"),
    USER_NOT_LOGIN(20003,"用户未登录"),
    USER_NOT_AUTHORIZED(20004,"用户权限不足"),

    //30000开头错误类型为交易信息错误
    STOCK_NOT_ENOUGH(30001,"商品库存不足")
    ;
    //错误代码
    private int errCode;
    //记录错误的信息
    private String errMsg;

    EmBussinessError(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }


    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
