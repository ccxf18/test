package com.demo.controller;

import com.demo.pojo.Page;
import com.demo.pojo.User;
import com.demo.result.ResultBody;
import com.demo.service.UserService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
@Api(value = "/user",tags = "用户模块")
public class UserController {
    @Autowired
    private UserService userService;

    //获取所有用户信息
    @ResponseBody
    @PostMapping(value = "/findAll", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "查询所有用户",notes = "分页查询所有用户",httpMethod = "POST")
    @ApiResponse(response = User.class,code=200,message = "操作成功")
    public ResultBody<List<User>> findAll(@RequestBody Page page) {
        List<User> recordList = userService.findAll(page);
        int sum = userService.loadpage();
        return ResultBody.ok().data(recordList).total(sum);
    }

    //获取单个用户信息
    @ResponseBody
    @GetMapping(value = "/findOne",produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "查询单个用户",notes = "查询单个用户",httpMethod = "GET")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> findOne(@Param("username")String username){
        User user = userService.selectByPrimaryKey(username);
        return ResultBody.ok().data(user);
    }

    //更新单个用户信息
    @ResponseBody
    @PostMapping (value = "/updateOne", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息",httpMethod = "POST")
    public ResultBody<String> updateOne(@RequestBody User user){
        userService.updateByPrimaryKeySelective(user);
        return ResultBody.ok().data("更新成功");
    }

    //禁用、解禁单个用户
    @ResponseBody
    @GetMapping(value = "/disableUser",produces = {"application/json;charset=UTF-8"})
    @ApiOperation("禁用/解禁单个用户")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> disableUser(@Param("username") String username){
        User user = userService.selectByPrimaryKey(username);
        if (user.getDisable() == 0){
            User user1 = new User();
            user1.setUsername(username);
            user1.setDisable(1);
            userService.updateByPrimaryKeySelective(user1);
            return ResultBody.ok().data("禁用成功");
        }else {
            User user2 = new User();
            user2.setUsername(username);
            user2.setDisable(0);
            userService.updateByPrimaryKeySelective(user2);
            return ResultBody.ok().data("解禁成功");
        }
    }

    //登录
    @ResponseBody
    @ApiOperation("登录")
    @GetMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,paramType = "query")
    })
    public ResultBody<String> login(@Param("username") String username, @Param("password") String password) {
        User user = userService.selectByUserNameAndPassword(username,password);
        if(user == null){
            //登录失败
            return ResultBody.failed().data("用户名或密码错误");
        }else{
            if(user.getDisable() == 1){
                return ResultBody.failed().data("该用户名已被禁用");
            }else{
                return ResultBody.ok().data("登陆成功");
            }
        }
    }

    //注册
    @ResponseBody
    @ApiOperation(value = "注册",notes = "注册",httpMethod = "POST")
    @PostMapping (value = "/register", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> register(@RequestBody User user) {
        Date date=new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String nowTime = df.format(date);
        if(userService.selectByPrimaryKey(user.getUsername()) != null){
            return ResultBody.failed().data("用户名已经存在");
        }
        else{
            userService.insertSelective(user);
            return ResultBody.ok().data("注册成功");
        }
    }

    //忘记密码
    @ResponseBody
    @ApiOperation(value = "忘记密码",notes = "忘记密码",httpMethod = "POST")
    @PostMapping (value = "/changePassword", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> changePassword(@RequestBody User user){
        User user1 = userService.selectByPrimaryKey(user.getUsername());
        if(user1.getEmail().equals(user.getEmail())){
            userService.updateByPrimaryKeySelective(user);
            return ResultBody.ok().data("重置密码成功");
        }else{
            return ResultBody.failed().data("用户名或邮箱错误，无法重置密码");
        }
    }

    //模糊查询
    @ResponseBody
    @ApiOperation("模糊查询")
    @GetMapping(value = "/fuzzyQuery", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "name",value = "真实姓名",required = true,paramType = "query")
    public ResultBody<List<User>> fuzzyQuery(@Param("name")String name){
        List<User> user = userService.fuzzyQuery(name);
        int len = user.size();
        return ResultBody.ok().data(user).total(len);
    }
}
