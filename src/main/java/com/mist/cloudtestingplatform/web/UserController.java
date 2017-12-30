package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Prophet on 2017/12/30.
 */
@Auth
@SessionAttributes("user")
@Controller
public class UserController {


    /***************************** pages *****************************/

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(@ModelAttribute User user) {
        return "home";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(@ModelAttribute User user) {
        return "user";
    }

}
