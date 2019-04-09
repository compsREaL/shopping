package com.real.shopping.error;

/**
 * @author: mabin
 * @create: 2019/4/4 16:41
 */
//
public class BussinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接受EmBusinessException作为参数
    public BussinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }
    //直接接受EmBusinessException作为参数，使用方法中传入的errMsg重写错误信息
    public BussinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
