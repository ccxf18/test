package com.demo.service.impl;

import com.demo.dao.CollectDao;
import com.demo.pojo.Collect;
import com.demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return collectDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Collect record) {
        return collectDao.insert(record);
    }

    @Override
    public int insertSelective(Collect record) {
        return collectDao.insertSelective(record);
    }

    @Override
    public Collect selectByPrimaryKey(Integer id) {
        return collectDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Collect record) {
        return collectDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collect record) {
        return collectDao.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByNotPrimaryKey(Collect record) {
        return collectDao.deleteByNotPrimaryKey(record);
    }

    @Override
    public Collect selectByNotPrimaryKey(Collect record) {
        return collectDao.selectByNotPrimaryKey(record);
    }
}
