package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Prophet on 2016/12/20.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void countUser() throws Exception {

    }

    @Test
    public void getUserByUsername() throws Exception {
        User user = userDao.getUserByUsername("slowfishcolor");
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void getUserById() throws Exception {
        User user = userDao.getUserById(1);
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void saveUser() throws Exception {

        User user = new User();
        user.setUsername("prophet");
        user.setPassword("prophet");
        user.setEmail("prophet_xu@163.com");
        user.setRole("user");
        userDao.saveUser(user);
    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void getAllUser() throws Exception {

    }

}