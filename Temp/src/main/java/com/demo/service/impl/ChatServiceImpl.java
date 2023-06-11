package com.demo.service.impl;

import com.demo.dao.ChatDao;
import com.demo.pojo.Chat;
import com.demo.pojo.Page;
import com.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return chatDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Chat record) {
        return chatDao.insert(record);
    }

    @Override
    public int insertSelective(Chat record) {
        return chatDao.insertSelective(record);
    }

    @Override
    public Chat selectByPrimaryKey(Integer id) {
        return chatDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Chat record) {
        return chatDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Chat record) {
        return chatDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Chat> findAll(Page page) {
        return chatDao.findAll(page);
    }

    @Override
    public List<Chat> fuzzyQuery(String content) {
        return chatDao.fuzzyQuery(content);
    }

    @Override
    public List<Chat> findAllCollect(String userName) {
        return chatDao.findAllCollect(userName);
    }

    @Override
    public List<Chat> findAllLike(String userName) {
        return chatDao.findAllLike(userName);
    }

    @Override
    public int loadpage() {
        return chatDao.loadpage();
    }
}
