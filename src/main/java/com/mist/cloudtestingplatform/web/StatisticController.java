package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.Task;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Prophet on 2018/3/2.
 */
@Auth
@SessionAttributes("user")
@Controller
public class StatisticController {

    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/listStatisticCount", method = RequestMethod.GET)
    public Map<String, String> getCurrentStatistic() {
        return statisticService.listStatisticCount();
    }

    @ResponseBody
    @RequestMapping(value = "/api/getNewDevice", method = RequestMethod.GET)
    public Device getNewDevice() {
        return statisticService.getNewDevice();
    }

    @ResponseBody
    @RequestMapping(value = "/api/getNewTask", method = RequestMethod.GET)
    public Task getNewTask(@ModelAttribute User user) {
        return statisticService.getNewTask(user.getId());
    }
}
