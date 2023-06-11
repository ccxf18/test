package com.demo.service.impl;

import com.demo.dao.SignDao;
import com.demo.pojo.Sign;
import com.demo.pojo.SignAll;
import com.demo.pojo.SignSelect;
import com.demo.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignDao signDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return signDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sign record) {
        return signDao.insert(record);
    }

    @Override
    public int insertSelective(Sign record) {
        return signDao.insertSelective(record);
    }

    @Override
    public Sign selectByPrimaryKey(Integer id) {
        return signDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Sign record) {
        return signDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Sign record) {
        return signDao.updateByPrimaryKey(record);
    }

    @Override
    public Integer selectByUserName(String userName) {
        return signDao.selectByUserName(userName);
    }

    @Override
    public List<SignAll> selectAll() {
        return signDao.selectAll();
    }

    @Override
    public Sign selectDayAndUserName(SignSelect record) {
        return signDao.selectDayAndUserName(record);
    }
}
