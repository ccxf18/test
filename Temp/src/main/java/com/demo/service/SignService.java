package com.demo.service;

import com.demo.pojo.Sign;
import com.demo.pojo.SignAll;
import com.demo.pojo.SignSelect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignService {
    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    Integer selectByUserName(@Param("userName")String userName);

    List<SignAll> selectAll();

    Sign selectDayAndUserName(SignSelect record);
}
