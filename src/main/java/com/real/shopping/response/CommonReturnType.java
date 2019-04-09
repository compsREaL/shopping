package com.real.shopping.response;

/**
 * @author: mabin
 * @create: 2019/4/4 16:13
 */
public class CommonReturnType {

    //对应请求返回的结果，"success"或"fail"
    private String status;

    //若status=success，则data返回前端需要的json数据；若status=fail，则data返回通用的错误格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object object){
        return CommonReturnType.create(object,"success");
    }

    public static CommonReturnType create(Object object, String status) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(object);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
