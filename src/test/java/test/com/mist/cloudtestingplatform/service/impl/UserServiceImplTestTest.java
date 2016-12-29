package test.com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Prophet on 2016/12/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserServiceImplTestTest {

    @Autowired
    UserService userService;

    @Test
    public void before() throws Exception {

    }

    @Test
    public void after() throws Exception {

    }

    @Test
    public void testCountUser() throws Exception {

    }

    @Test
    public void testGetUserByUsername() throws Exception {

    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userService.getUserById(1);
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testGetUserByPassword() throws Exception {
        User user = userService.getUserByPassword("slowfishcolor", "slowfishcolor");
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @Test
    public void testGetAllUser() throws Exception {

    }

}