package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
@ApiModel(description = "用户数据")
public class User implements Serializable {
    @ApiModelProperty(value = "用户主键")
    private String username;
    @ApiModelProperty(value = "登录密码")
    private String userpwd;
    @ApiModelProperty(value = "是否禁用")
    private Integer disable;
    @ApiModelProperty(value = "注册时间")
    private Date time;
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    private static final long serialVersionUID = 1L;

    public User(String username,String userpwd,int disable,Date time,String email,String gender,String address,String phone,int age){
        this.username = username;
        this.userpwd = userpwd;
        this.disable = disable;
        this.time = time;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }
    public  User() {

    }

}