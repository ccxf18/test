package com.demo.dao;

import com.demo.pojo.Page;
import com.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    List<User> findAll(Page page);

    int loadpage();

    int updateByPrimaryKey(User record);

    User selectByUserNameAndPassword(@Param("username")String username,@Param("password") String password);

    List<User> fuzzyQuery(@Param("name")String realname);
}