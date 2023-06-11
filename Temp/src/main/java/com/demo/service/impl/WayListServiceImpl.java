package com.demo.service.impl;

import com.demo.dao.WayListDao;
import com.demo.pojo.WayList;
import com.demo.service.WayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WayListServiceImpl implements WayListService {
    @Autowired
    private WayListDao wayListDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wayListDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WayList record) {
        return wayListDao.insert(record);
    }

    @Override
    public int insertSelective(WayList record) {
        return wayListDao.insertSelective(record);
    }

    @Override
    public WayList selectByPrimaryKey(Integer id) {
        return wayListDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WayList record) {
        return wayListDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WayList record) {
        return wayListDao.updateByPrimaryKey(record);
    }

    @Override
    public List<WayList> findAll() {
        return wayListDao.findAll();
    }
}
