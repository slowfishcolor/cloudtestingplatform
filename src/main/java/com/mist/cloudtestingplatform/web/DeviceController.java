package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "/device-control-panel/{deviceId}", method = RequestMethod.GET)
    public String controlPanelPage(@PathVariable("deviceId") String deviceId, Model model) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        logger.info("deviceId: " + deviceId);
        return "device-control-panel-pcie6320";
    }
}
