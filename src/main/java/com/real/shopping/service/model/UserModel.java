package com.real.shopping.service.model;

import com.real.shopping.dataobject.UserRoleDO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author: mabin
 * @create: 2019/4/4 16:55
 */
public class UserModel implements Serializable {

    private Integer id;
    @NotBlank(message = "请填写您的用户名")
    private String name;
    @NotNull(message = "请填写您的年龄")
    @Max(value = 150,message = "年龄不得大于150岁")
    @Min(value = 0,message = "年龄不得小于0岁")
    private Integer age;
    @NotNull(message = "请填写您的姓名")
    private Byte gender;
    @NotBlank(message = "请填写您的密码")
    private String encrptPassword;
    @NotBlank(message = "请填写您的手机号码")
    private String telephone;

    private List<UserRoleModel> roleModelList;

    public List<UserRoleModel> getRoleModelList() {
        return roleModelList;
    }

    public void setRoleModelList(List<UserRoleModel> roleModelList) {
        this.roleModelList = roleModelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
