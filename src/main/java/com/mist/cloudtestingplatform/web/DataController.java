package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Prophet on 2017/11/26.
 */
@Auth
@SessionAttributes("user")
@Controller
public class DataController {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/queryDataListByPage", method = RequestMethod.POST)
    public Map<String, Object> queryDataListByPage(@ModelAttribute User user, String deviceId, Integer page) {
        Map<String, Object> results = new HashMap<>();
        results.put("code", 200);
        results.put("datas", dataService.listDataByPage(deviceId, user.getId(), page, 10));
        results.put("totalCount", dataService.listDataCount(deviceId,user.getId()));
        results.put("currentPage", page);
        return results;
    }

}
