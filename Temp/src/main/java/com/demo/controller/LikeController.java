package com.demo.controller;

import com.demo.pojo.Chat;
import com.demo.pojo.Collect;
import com.demo.pojo.Like;
import com.demo.result.ResultBody;
import com.demo.service.ChatService;
import com.demo.service.CollectService;
import com.demo.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/like")
@Api(value = "/like",tags = "喜欢模块")

public class LikeController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private LikeService likeService;

    //用户喜欢某条
    //1.like库中加入
    //2.chat库中收藏数+1
    @ResponseBody
    @ApiOperation(value = "用户喜欢某条评论",notes = "传评论id、userName",httpMethod = "POST")
    @PostMapping(value = "/confirmLike", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> confirmLike(@RequestBody Like like){
        likeService.insertSelective(like);
        Chat chat = chatService.selectByPrimaryKey(like.getChatId());
        Integer num = chat.getLike();
        chat.setLike(num+1);
        chatService.updateByPrimaryKeySelective(chat);
        return ResultBody.ok().data("喜欢成功");
    }

    //用户取消喜欢某条
    //1.like库中删除
    //2.chat库中收藏数-1
    @ResponseBody
    @ApiOperation(value = "用户取消喜欢某条评论",notes = "传评论id、userName",httpMethod = "POST")
    @PostMapping(value = "/cancelLike", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> cancelLike(@RequestBody Like like){
        likeService.deleteByNotPrimaryKey(like);
        Chat chat = chatService.selectByPrimaryKey(like.getChatId());
        Integer num = chat.getLike();
        chat.setLike(num-1);
        chatService.updateByPrimaryKeySelective(chat);
        return ResultBody.ok().data("取消喜欢成功");
    }
}
