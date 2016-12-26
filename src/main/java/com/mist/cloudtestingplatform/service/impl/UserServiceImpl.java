package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prophet on 2016/12/24.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int countUser() {
        return userDao.countUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    /**
     * 通过用户名和密码拿到用户
     *
     * @param username
     * @param password
     * @return 如果用户名密码匹配，返回该User，若无，User为null
     */
    @Override
    public User getUserByPassword(String username, String password) {
        return userDao.getUserByPassword(username, password);
    }

    @Override
    public void addUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
