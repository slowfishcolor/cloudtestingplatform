package com.mist.cloudtestingplatform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户界面相关
 * Created by Prophet on 2016/12/18.
 */
@Controller
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String user(@PathVariable("username") String username, Model model) {
        logger.info("Current user: " + username);
        model.addAttribute("username", username);
        return "user";
    }
}
