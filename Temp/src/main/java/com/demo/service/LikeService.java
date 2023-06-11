package com.demo.service;

import com.demo.pojo.Like;

public interface LikeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    int deleteByNotPrimaryKey(Like record);

    Like selectByNotPrimaryKey(Like record);
}
