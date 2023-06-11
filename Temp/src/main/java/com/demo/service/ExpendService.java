package com.demo.service;

import com.demo.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpendService {
    int deleteByPrimaryKey(Integer id);

    int insert(Expend record);

    int insertSelective(Expend record);

    Expend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Expend record);

    int updateByPrimaryKey(Expend record);

    List<Expend> findAllByUserName(@Param("userName")String userName);

    List<Count> countDay(@Param("userName")String userName);

    List<CountMonth> countMonth(@Param("userName")String userName);

    List<Expend> findAll(Page page);

    int loadpage();
}
