package com.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.*;
import com.demo.result.ResultBody;
import com.demo.service.ExpendService;
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
@RequestMapping(value = "/expend")
@Api(value = "/expend",tags = "支出模块")
public class ExpendController {
    @Autowired
    private ExpendService expendService;
    @Autowired
    private WayListService wayListService;

    //获取所有支出信息
    @ResponseBody
    @PostMapping(value = "/findAll", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "获取所有支出信息",notes = "分页获取所有支出信息",httpMethod = "POST")
    @ApiResponse(response = Expend.class,code=200,message = "操作成功")
    public ResultBody<List<Expend>> findAll(@RequestBody Page page) {
        List<Expend> recordList = expendService.findAll(page);
        return ResultBody.ok().data(recordList).total(expendService.loadpage());
    }

    //根据用户名查询所有支出记录
    @ResponseBody
    @ApiOperation("根据用户名查询所有支出记录")
    @GetMapping(value = "/findByUserName", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> findAll(@Param("userName")String userName) {
        JSONObject data = new JSONObject();
        List<Expend> recordList = expendService.findAllByUserName(userName);
        List<WayList> wayLists = wayListService.findAll();
        data.put("data",recordList);
        data.put("way",wayLists);
        return ResultBody.ok().data(data).total(recordList.size());
    }

    //新增支出
    @ResponseBody
    @ApiOperation(value = "新增支出",notes = "只需要传userName、name（支出项目）、way、sum、time(不传时间默认当前时间)",httpMethod = "POST")
    @PostMapping (value = "/insert", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> insert(@RequestBody Expend expend){
        if(expend.getTime() != null){
            expendService.insertSelective(expend);
            return ResultBody.ok().data("新增成功");
        }else {
            Date date = new Date();
            expend.setTime(date);
            expendService.insertSelective(expend);
            return ResultBody.ok().data("新增成功");
        }
    }

    //修改某条支出
    @ResponseBody
    @ApiOperation(value = "修改某条支出",notes = "只需要传id以及修改的内容",httpMethod = "POST")
    @PostMapping (value = "/update", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> update(@RequestBody Expend expend){
        expendService.updateByPrimaryKeySelective(expend);
        return ResultBody.ok().data("修改成功");
    }

    //删除某条支出
    @ResponseBody
    @DeleteMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "删除某条支出",notes = "根据id删除某条收入",httpMethod = "DELETE")
    @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query")
    public ResultBody<String> delete(@Param("id")Integer id){
        expendService.deleteByPrimaryKey(id);
        return ResultBody.ok().data("删除成功");
    }

    //批量删除支出
    @ResponseBody
    @DeleteMapping(value = "/deleteBatch", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "批量删除支出",notes = "根据id批量删除支出",httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids",value = "ids",required = true,paramType = "query")
    public ResultBody<String> deleteBatch(@Param("ids")Integer[] ids){
        for (Integer id:ids){
            expendService.deleteByPrimaryKey(id);
        }
        return ResultBody.ok().data("批量删除成功");
    }

    //获取统计数据(天维度)
    @ResponseBody
    @ApiOperation(value = "获取统计数据(天维度),用于折线图",notes = "标题：支出统计(天)",httpMethod = "GET")
    @GetMapping(value = "/countDay", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<String> countDay(@Param("userName")String userName) {
        List<Count> recordList = expendService.countDay(userName);
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
    @ApiOperation(value = "获取统计数据(月维度),用于饼图",notes = "标题：支出统计(月)",httpMethod = "GET")
    @GetMapping(value = "/countMonth", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query")
    public ResultBody<List<CountMonth>> countMonth(@Param("userName")String userName) {
        List<CountMonth> countMonths = expendService.countMonth(userName);
        return ResultBody.ok().data(countMonths).total(countMonths.size());
    }

}
