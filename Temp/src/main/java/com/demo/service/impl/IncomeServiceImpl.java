package com.demo.service.impl;

import com.demo.dao.IncomeDao;
import com.demo.pojo.Count;
import com.demo.pojo.CountMonth;
import com.demo.pojo.Income;
import com.demo.pojo.Page;
import com.demo.service.IncomeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeDao incomeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return incomeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Income record) {
        return incomeDao.insert(record);
    }

    @Override
    public int insertSelective(Income record) {
        return incomeDao.insertSelective(record);
    }

    @Override
    public Income selectByPrimaryKey(Integer id) {
        return incomeDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Income record) {
        return incomeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Income record) {
        return incomeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Income> findAllByUserName(String userName) {
        return incomeDao.findAllByUserName(userName);
    }

    @Override
    public List<Count> countDay(@Param("userName")String userName) {
        return incomeDao.countDay(userName);
    }

    @Override
    public List<CountMonth> countMonth(String userName) {
        return incomeDao.countMonth(userName);
    }

    @Override
    public List<Income> findAll(Page page) {
        return incomeDao.findAll(page);
    }

    @Override
    public int loadpage() {
        return incomeDao.loadpage();
    }
}
