package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 用户登陆、注册、界面相关
 * Created by Prophet on 2016/12/18.
 */
@Controller
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户登陆界面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 用户登陆验证请求，Post请求
     * @param username
     * @param password
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        // 利用表单参数查询用户
        User user = userService.getUserByPassword(username, password);
        if (user == null) {
            model.addAttribute("error", "用户名或密码错误！");
            logger.info("用户名或密码错误！");
            return "login";
        } else {
            // 登陆成功则设置session
            session.setAttribute("user", user);

            logger.info(user.getUsername() + "成功登陆");

            // 获得重定向路径
            String path = "/user/" + user.getId();

            // 重定向到用户界面
            return "redirect:" + path;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String user(@PathVariable("userId") Integer userId, Model model, HttpSession session) {

        // 从session中尝试获取user
        User user = (User) session.getAttribute("user");

        // 如果session中没有user或者user的id与当前要访问的用户id不同，则提示未登陆
        if (user == null || user.getId() != userId) {
            model.addAttribute("error", "用户未登陆！");
            logger.info("用户未登陆！");
            return "login";
        }

        // 如果session 中user 存在且是要访问的用户，则显示用户界面
        model.addAttribute("user", user);
        logger.info(user.getUsername() + "成功登陆");
        return "user";
    }

    @RequestMapping(value = "/logout/{page}", method = RequestMethod.GET)
    public  String logout(@PathVariable("page") String page, HttpSession session) {

        session.removeAttribute("user");

        String path = "/login";

        if(page.equals("main")) path = "/";

        return "redirect:" + path;
    }
}
