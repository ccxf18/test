package com.demo.service.impl;

import com.demo.dao.LikeDao;
import com.demo.pojo.Like;
import com.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeDao likeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return likeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Like record) {
        return likeDao.insert(record);
    }

    @Override
    public int insertSelective(Like record) {
        return likeDao.insertSelective(record);
    }

    @Override
    public Like selectByPrimaryKey(Integer id) {
        return likeDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Like record) {
        return likeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Like record) {
        return likeDao.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByNotPrimaryKey(Like record) {
        return likeDao.deleteByNotPrimaryKey(record);
    }

    @Override
    public Like selectByNotPrimaryKey(Like record) {
        return likeDao.selectByNotPrimaryKey(record);
    }
}
