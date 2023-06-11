package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.pojo.Page;
import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectByPrimaryKey(String username) {
        return userDao.selectByPrimaryKey(username);
    }

    @Override
    public List<User> findAll(Page page) {
        return userDao.findAll(page);
    }

    @Override
    public int loadpage() {
        return userDao.loadpage();
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByUserNameAndPassword(String username, String password) {
        return userDao.selectByUserNameAndPassword(username,password);
    }

    @Override
    public void insertSelective(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public List<User> fuzzyQuery(String realname) {
        return userDao.fuzzyQuery(realname);
    }
}
