package com.demo.service;

import com.demo.pojo.Chat;
import com.demo.pojo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatService {
    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    List<Chat> findAll(Page page);

    List<Chat> fuzzyQuery(@Param("content")String content);

    List<Chat> findAllCollect(@Param("userName")String userName);

    List<Chat> findAllLike(@Param("userName")String userName);

    int loadpage();
}
