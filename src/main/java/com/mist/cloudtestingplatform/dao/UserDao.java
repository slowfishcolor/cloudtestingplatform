package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Prophet on 2016/12/20.
 */
@Repository
public interface UserDao {

    int countUser();

    User getUserByUsername(String username);

    User getUserById(int id);

    void saveUser(User user);

    void deleteUser(User user);

    List<User> getAllUser();

}
