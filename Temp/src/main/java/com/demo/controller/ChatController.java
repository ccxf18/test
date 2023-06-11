package com.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Chat;
import com.demo.pojo.Collect;
import com.demo.pojo.Like;
import com.demo.pojo.Page;
import com.demo.result.ResultBody;
import com.demo.service.ChatService;
import com.demo.service.CollectService;
import com.demo.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/chat")
@Api(value = "/chat",tags = "社区模块")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CollectService collectService;

    //查询所有社区评论
    @ResponseBody
    @PostMapping(value = "/findAll", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "查询所有社区评论",notes = "分页查询所有社区评论，page/pagesize必传",httpMethod = "POST")
    @ApiResponse(response = Chat.class,code=200,message = "操作成功")
    public ResultBody<List<Chat>> findAll(@RequestBody Page page) {
        List<Chat> recordList = chatService.findAll(page);
        String useName = page.getUserName();
        if (useName != null){
            for(Chat item:recordList){
                Like like = new Like();
                like.setChatId(item.getId());
                like.setUserName(useName);
                Like res = likeService.selectByNotPrimaryKey(like);
                if (res != null){
                    item.setLikeStatus(true);
                }else {
                    item.setLikeStatus(false);
                }

                Collect collect = new Collect();
                collect.setChatId(item.getId());
                collect.setUserName(useName);
                Collect res1 = collectService.selectByNotPrimaryKey(collect);
                if (res1 != null){
                    item.setCollectStatus(true);
                }else {
                    item.setCollectStatus(false);
                }
            }
        }
        return ResultBody.ok().data(recordList).total(chatService.loadpage());
    }

    //根据内容模糊查询
    @ResponseBody
    @ApiOperation("根据内容模糊查询")
    @GetMapping(value = "/fuzzyQuery", produces = {"application/json;charset=UTF-8"})
    @ApiImplicitParam(name = "content",value = "内容",required = true,paramType = "query")
    public ResultBody<List<Chat>> fuzzyQuery(@Param("content")String content){
        List<Chat> news = chatService.fuzzyQuery(content);
        return ResultBody.ok().data(news).total(news.size());
    }

    //发布社区评论
    @ResponseBody
    @ApiOperation(value = "发布社区评论",notes = "只需要传author、content、time,时间不传默认为当前时间",httpMethod = "POST")
    @PostMapping (value = "/insert", produces = {"application/json;charset=UTF-8"})
    public ResultBody<String> insert(@RequestBody Chat chat){
        if (chat.getTime() != null) {
            chatService.insertSelective(chat);
            return ResultBody.ok().data("新增成功");
        }else {
            Date date = new Date();
            chat.setTime(date);
            chatService.insertSelective(chat);
            return ResultBody.ok().data("新增成功");
        }

    }

    //删除社区评论
    @ResponseBody
    @DeleteMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "删除社区评论",notes = "根据id删除社区评论",httpMethod = "DELETE")
    @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query")
    public ResultBody<String> delete(@Param("id")Integer id){
        chatService.deleteByPrimaryKey(id);
        return ResultBody.ok().data("删除成功");
    }

    //批量删除社区评论
    @ResponseBody
    @DeleteMapping(value = "/deleteBatch", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "批量删除社区评论",notes = "根据id批量删除社区评论",httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids",value = "ids",required = true,paramType = "query")
    public ResultBody<String> deleteBatch(@Param("ids")Integer[] ids){
        for (Integer id:ids){
            chatService.deleteByPrimaryKey(id);
        }
        return ResultBody.ok().data("批量删除成功");
    }
}
