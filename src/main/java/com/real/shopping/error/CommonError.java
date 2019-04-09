package com.real.shopping.error;

/**
 * @author: mabin
 * @create: 2019/4/4 16:21
 */
public interface CommonError {
    int getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
