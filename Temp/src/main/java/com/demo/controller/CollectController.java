package com.demo.controller;

import com.demo.pojo.Chat;
import com.demo.pojo.Collect;
import com.demo.result.ResultBody;
import com.demo.service.ChatService;
import com.demo.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/collect")
@Api(value = "/collect",tags = "收藏模块")

public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private ChatService chatService;

    //用户收藏某条
    //1.collect库中加入
    //2.chat库中收藏数+1
    @ResponseBody
    @ApiOperation(value = "用户收藏某条评论",notes = "传评论id、userName",httpMethod = "POST")
    @PostMapping(value = "/confirmCollect", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> confirmCollect(@RequestBody Collect collect){
        collectService.insertSelective(collect);
        Chat chat = chatService.selectByPrimaryKey(collect.getChatId());
        Integer num = chat.getCollect();
        chat.setCollect(num+1);
        chatService.updateByPrimaryKeySelective(chat);
        return ResultBody.ok().data("收藏成功");
    }

    //用户取消收藏某条
    //1.collect库中删除
    //2.chat库中收藏数-1
    @ResponseBody
    @ApiOperation(value = "用户取消收藏某条评论",notes = "传评论id、userName",httpMethod = "POST")
    @PostMapping(value = "/cancelCollect", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> cancelCollect(@RequestBody Collect collect){
        collectService.deleteByNotPrimaryKey(collect);
        Chat chat = chatService.selectByPrimaryKey(collect.getChatId());
        Integer num = chat.getCollect();
        chat.setCollect(num-1);
        chatService.updateByPrimaryKeySelective(chat);
        return ResultBody.ok().data("取消收藏成功");
    }
}
