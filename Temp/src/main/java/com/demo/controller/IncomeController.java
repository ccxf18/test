package com.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.*;
import com.demo.result.ResultBody;
import com.demo.service.IncomeService;
import com.demo.service.WayListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/income")
@Api(value = "/income",tags = "收入模块")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private WayListService wayListService;

    //获取所有收入信息
    @ResponseBody
    @PostMapping(value = "/findAll", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "获取所有收入信息",notes = "分页获取所有收入信息",httpMethod = "POST")
    @ApiResponse(response = Income.class,code=200,message = "操作成功")
    public ResultBody<List<Income>> findAll(@RequestBody Page page) {
        List<Income> recordList = incomeService.findAll(page);
        return ResultBody.ok().data(recordList).total(incomeService.loadpage());
    }

    //根据用户名查询所有收入记录
    @ResponseBody
    @ApiOperation("根据用户名查询所有收入记录")
    @GetMapping(value = "/findByUserName", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> findAll(@Param("userName")String userName) {
        JSONObject data = new JSONObject();
        List<Income> recordList = incomeService.findAllByUserName(userName);
        List<WayList> wayLists = wayListService.findAll();
        data.put("data",recordList);
        data.put("way",wayLists);
        return ResultBody.ok().data(data).total(recordList.size());
    }

    //新增收入
    @ResponseBody
    @ApiOperation(value = "新增收入",notes = "只需要传userName、name（支出项目）、way、sum、time(不传时间默认当前时间)",httpMethod = "POST")
    @PostMapping (value = "/insert", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> insert(@RequestBody Income income){
        if(income.getTime() != null){
            incomeService.insertSelective(income);
            return ResultBody.ok().data("新增成功");
        }else {
            Date date = new Date();
            income.setTime(date);
            incomeService.insertSelective(income);
            return ResultBody.ok().data("新增成功");
        }
    }

    //修改某条收入
    @ResponseBody
    @ApiOperation(value = "修改某条收入",notes = "只需要传id以及修改的内容",httpMethod = "POST")
    @PostMapping (value = "/update", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> update(@RequestBody Income income){
        incomeService.updateByPrimaryKeySelective(income);
        return ResultBody.ok().data("修改成功");
    }

    //删除某条收入
    @ResponseBody
    @DeleteMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "删除某条收入",notes = "根据id删除某条收入",httpMethod = "DELETE")
    @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query")
    public ResultBody<String> delete(@Param("id")Integer id){
        incomeService.deleteByPrimaryKey(id);
        return ResultBody.ok().data("删除成功");
    }

    //批量删除收入
    @ResponseBody
    @DeleteMapping(value = "/deleteBatch", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "批量删除收入",notes = "根据id批量删除收入",httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids",value = "ids",required = true,paramType = "query")
    public ResultBody<String> deleteBatch(@Param("ids")Integer[] ids){
        for (Integer id:ids){
            incomeService.deleteByPrimaryKey(id);
        }
        return ResultBody.ok().data("批量删除成功");
    }

    //获取统计数据(天维度)
    @ResponseBody
    @ApiOperation(value = "获取统计数据(天维度),用于折线图",notes = "标题：收入统计(天)",httpMethod = "GET")
    @GetMapping(value = "/countDay", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> countDay(@Param("userName")String userName) {
        List<Count> recordList = incomeService.countDay(userName);
        JSONObject data = new JSONObject();
        List<Integer> total_list = new ArrayList<Integer>();
        List<String> day_list = new ArrayList<String>();
        for(Count item:recordList){
            total_list.add(item.getTotal());
            day_list.add(item.getDay());
        }
        data.put("xData",day_list);
        data.put("yData",total_list);
        return ResultBody.ok().data(data).total(recordList.size());
    }

    //获取统计数据(月维度)
    @ResponseBody
    @ApiOperation(value = "获取统计数据(月维度),用于饼图",notes = "标题：收入统计(月)",httpMethod = "GET")
    @GetMapping(value = "/countMonth", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<List<CountMonth>> countMonth(@Param("userName")String userName) {
        List<CountMonth> countMonths = incomeService.countMonth(userName);
        return ResultBody.ok().data(countMonths).total(countMonths.size());
    }

}
