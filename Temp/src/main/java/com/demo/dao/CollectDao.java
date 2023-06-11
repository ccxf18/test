package com.demo.dao;

import com.demo.pojo.Collect;

public interface CollectDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int deleteByNotPrimaryKey(Collect record);

    Collect selectByNotPrimaryKey(Collect record);
}