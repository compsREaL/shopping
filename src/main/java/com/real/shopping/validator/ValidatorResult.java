package com.real.shopping.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: mabin
 * @create: 2019/4/8 9:04
 */
public class ValidatorResult {

    //校验结果是否有误
    private boolean hasErrors = false;
    //使用map集合存放返回的错误
    private Map<String,String> errMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }

    public String getErrMsg(){
        return StringUtils.join(errMsgMap.values().toArray(),",");
    }
}
