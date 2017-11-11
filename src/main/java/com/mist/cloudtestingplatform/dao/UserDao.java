package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2016/12/20.
 */
@Repository
public interface UserDao {

    int countUser();

    User getUserByUsername(String username);

    User getUserById(int id);

    /**
     * 根据用户名和密码访问用户数据
     * @param username
     * @param password
     * @return 如果用户名密码匹配，返回该User，若无，User为null
     */
    User getUserByPassword(String username, String password);

    void saveUser(User user);

    void deleteUser(User user);

    List<User> getAllUser();

    List<User> getUserIn(Collection<Integer> idList);

}
