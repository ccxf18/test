package com.demo.service;

import com.demo.pojo.WayList;

import java.util.List;

public interface WayListService {
    int deleteByPrimaryKey(Integer id);

    int insert(WayList record);

    int insertSelective(WayList record);

    WayList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WayList record);

    int updateByPrimaryKey(WayList record);

    List<WayList> findAll();
}
