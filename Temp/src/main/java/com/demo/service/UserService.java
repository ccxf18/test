package com.demo.service;

import com.demo.pojo.Page;
import com.demo.pojo.User;

import java.util.List;

/**
 * @author
 * @Create 2022年10月21日16:58:54
 * @description   业务层   增删改查
 */
public interface UserService {

    User selectByPrimaryKey(String username);

    List<User> findAll(Page page);

    int loadpage();

    void updateByPrimaryKeySelective(User user);

    User selectByUserNameAndPassword(String username,String password);

    void insertSelective(User user);

    List<User> fuzzyQuery(String realname);
}
