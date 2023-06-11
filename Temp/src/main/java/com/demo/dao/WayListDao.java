package com.demo.dao;

import com.demo.pojo.WayList;

import java.util.List;

public interface WayListDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WayList record);

    int insertSelective(WayList record);

    WayList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WayList record);

    int updateByPrimaryKey(WayList record);

    List<WayList> findAll();
}