package com.demo.dao;

import com.demo.pojo.Count;
import com.demo.pojo.CountMonth;
import com.demo.pojo.Income;
import com.demo.pojo.Page;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    List<Income> findAllByUserName(@Param("userName")String userName);

    List<Count> countDay(@Param("userName")String userName);

    List<CountMonth> countMonth(@Param("userName")String userName);

    List<Income> findAll(Page page);

    int loadpage();

}