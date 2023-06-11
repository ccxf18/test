package com.demo.service.impl;

import com.demo.dao.ExpendDao;
import com.demo.pojo.*;
import com.demo.service.ExpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpendServiceImpl implements ExpendService {
    @Autowired
    private ExpendDao expendDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return expendDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Expend record) {
        return expendDao.insert(record);
    }

    @Override
    public int insertSelective(Expend record) {
        return expendDao.insertSelective(record);
    }

    @Override
    public Expend selectByPrimaryKey(Integer id) {
        return expendDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Expend record) {
        return expendDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Expend record) {
        return expendDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Expend> findAllByUserName(String userName) {
        return expendDao.findAllByUserName(userName);
    }

    @Override
    public List<Count> countDay(String userName) {
        return expendDao.countDay(userName);
    }

    @Override
    public List<CountMonth> countMonth(String userName) {
        return expendDao.countMonth(userName);
    }

    @Override
    public List<Expend> findAll(Page page) {
        return expendDao.findAll(page);
    }

    @Override
    public int loadpage() {
        return expendDao.loadpage();
    }
}
