package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.MappingModel;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.DeviceService;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * device related pages
 * Created by Prophet on 2017/11/4.
 */
@Auth
@SessionAttributes("user")
@Controller
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /***************************** pages *****************************/

    @RequestMapping(value = "/device-control-panel/{deviceId}", method = RequestMethod.GET)
    public String controlPanelPage(@PathVariable("deviceId") String deviceId, Model model, @ModelAttribute User user) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        logger.info("deviceId: " + deviceId);

        String viewName = device.getModel().getView();
        if (viewName == null || "".equals(viewName)) {
            viewName = "viewName";
        }
        return "device-control-panel-" + viewName;
    }

    @RequestMapping(value = "/device-list", method = RequestMethod.GET)
    public String deviceListPage(@ModelAttribute User user, Model model, HttpServletRequest request) {
        List<Device> devices = deviceService.listVisibleDeviceByUser(user.getId());
        model.addAttribute("devices", devices);

        List<String> deviceIdList = new ArrayList<>();
        for(Device device: devices) {
            deviceIdList.add(device.getDeviceId());
        }
        request.getSession().setAttribute("deviceIdList", deviceIdList);

        return "device-list";
    }

    @RequestMapping(value = "/device-info/{deviceId}", method = RequestMethod.GET)
    public String deviceInfoPage(@PathVariable("deviceId") String deviceId,@ModelAttribute User user, Model model) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        return "device-info";
    }

    @RequestMapping(value = "/device-history/{deviceId}", method = RequestMethod.GET)
    public String deviceHistoryPage(@PathVariable("deviceId") String deviceId,@ModelAttribute User user, Model model) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        return "device-history";
    }

    @RequestMapping(value = "/device-mapping/{deviceId}", method = RequestMethod.GET)
    public String deviceMappingPage(@PathVariable("deviceId") String deviceId,@ModelAttribute User user, Model model) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        return "device-mapping";
    }

    @RequestMapping(value = "/device-add", method = RequestMethod.GET)
    public String deviceAddPage(@ModelAttribute User user) {
        return "device-add";
    }

    @RequestMapping(value = "/device-description/{deviceId}", method = RequestMethod.GET)
    public String deviceDescriptionPage(@PathVariable("deviceId") String deviceId,@ModelAttribute User user, Model model) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        model.addAttribute(device);
        return "device-description";
    }


    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/deleteDevice", method = RequestMethod.POST)
    public OperateResult deleteDevice(String deviceIdStrs, @ModelAttribute User user) {
        return deviceService.deleteDevice(deviceIdStrs, user.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/updateDeviceConfig", method = RequestMethod.POST)
    public OperateResult updateDeviceConfig(String deviceId, String config, @ModelAttribute User user) {
        return deviceService.updateDeviceConfig(deviceId, config);
    }

    @ResponseBody
    @RequestMapping(value = "/api/generateDeviceId", method = RequestMethod.POST)
    public String generateDeviceId() {
        return IdUtils.generateDeviceId();
    }

    @ResponseBody
    @RequestMapping(value = "/api/getAllDevice", method = RequestMethod.GET)
    public List<Device> getAllDevice(@ModelAttribute User user) {
        return deviceService.listVisibleDeviceByUser(user.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/api/getDeviceMapping/{deviceId}", method = RequestMethod.GET)
    public MappingModel getDeviceMapping(@PathVariable("deviceId") String deviceId) {
        return deviceService.getDeviceMapping(deviceId);
    }

}
