package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.User;

import java.util.List;

/**
 * Created by Prophet on 2016/12/24.
 */
public interface UserService {

    int countUser();

    User getUserByUsername(String username);

    User getUserById(int id);

    /**
     * 通过用户名和密码拿到用户
     * @param username
     * @param password
     * @return  如果用户名密码匹配，返回该User，若无，User为null
     */
    User getUserByPassword(String username, String password);

    void addUser(User user);

    void deleteUser(User user);

    List<User> getAllUser();
}
