package com.demo.controller;

import com.demo.pojo.Chat;
import com.demo.pojo.Sign;
import com.demo.pojo.SignAll;
import com.demo.pojo.SignSelect;
import com.demo.result.ResultBody;
import com.demo.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/sign")
@Api(value = "/sign",tags = "签到模块")
public class SignController {
    @Autowired
    private SignService signService;

    //签到天数
    @ResponseBody
    @ApiOperation(value = "用户总签到天数-用户端",notes = "传userName",httpMethod = "GET")
    @GetMapping(value = "/signDay", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> signDay(@Param("userName")String userName){
        Integer sum = signService.selectByUserName(userName);
        return ResultBody.ok().data(sum);
    }

    //用户签到
    @ResponseBody
    @ApiOperation(value = "用户签到",notes = "传userName,一天只能签到一次，当天签到过会返回'今天已经签到啦'，没签到过会返回'签到成功'",httpMethod = "POST")
    @GetMapping(value = "/sign", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> sign(@Param("userName")String userName){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today_date = sdf.format(date);
        SignSelect sign = new SignSelect();
        sign.setTime(today_date);
        sign.setUserName(userName);
        Sign sign1 = signService.selectDayAndUserName(sign);
        if(sign1 == null){
            Sign sign2 = new Sign();
            sign2.setTime(date);
            sign2.setUserName(userName);
            signService.insertSelective(sign2);
            return ResultBody.ok().data("签到成功");
        }else{
            return ResultBody.ok().data("今天已经签到啦");
        }
    }

    //所有用户签到情况
    @ResponseBody
    @ApiOperation(value = "所有用户签到情况-管理端",notes = "",httpMethod = "GET")
    @GetMapping(value = "/signAll", produces = {"application/json;charset=UTF-8"})
    public ResultBody<List<SignAll>> signAll(){
        List<SignAll> signAll = signService.selectAll();
        return ResultBody.ok().data(signAll);
    }
}
