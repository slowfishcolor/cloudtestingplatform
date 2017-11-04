package com.mist.cloudtestingplatform.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * device related pages
 * Created by Prophet on 2017/11/4.
 */
@Controller
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @RequestMapping(value = "/device/{deviceId}", method = RequestMethod.GET)
    public String controlPanelPage(@PathVariable("deviceId") Integer deviceId) {
        logger.info("deviceId: " + deviceId);
        return "device-control-panel";
    }
}
