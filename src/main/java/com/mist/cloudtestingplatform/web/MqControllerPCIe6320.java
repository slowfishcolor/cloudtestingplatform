package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.service.MQServicePCIe6320;
import com.mist.cloudtestingplatform.service.OperateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Prophet on 2017/12/22.
 */
@Auth
@SessionAttributes("user")
@Controller
public class MqControllerPCIe6320 implements MqController {

    private static final Logger logger = LoggerFactory.getLogger(MqControllerPCIe6320.class);

    private MQServicePCIe6320 mqServicePCIe6320;

    @Autowired
    public void setMqService(MQServicePCIe6320 mqServicePCIe6320) {
        this.mqServicePCIe6320 = mqServicePCIe6320;
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/mq/pcie6320/sendSingleCommand", method = RequestMethod.POST)
    public OperateResult sendSingleCommand(String deviceId, String command) {
        logger.info("deviceId: {}, command: {}", deviceId, command);
        return mqServicePCIe6320.sendSingleCommand(deviceId, command);
    }

    @ResponseBody
    @RequestMapping(value = "/api/mq/pcie6320/sendStartStopCommand", method = RequestMethod.POST)
    public OperateResult sendStartStopCommand(String deviceId, boolean isStart) {
        logger.info("deviceId: {}, command: isStart = {}", deviceId, isStart);
        return mqServicePCIe6320.sendStartStopCommand(deviceId, isStart);
    }

    @ResponseBody
    @RequestMapping(value = "/api/mq/pcie6320/sendSetupCommand", method = RequestMethod.POST)
    public OperateResult sendSetupCommand(String deviceId, String channel, String method, float minVoltage, float maxVoltage, int samples, float rate, String command) {
        logger.info("deviceId: {}, command: setup,  channel {}, rate {}" , new Object[] {deviceId, channel, rate});
        return mqServicePCIe6320.sendSetupCommand(deviceId, channel, method, minVoltage, maxVoltage, samples, rate, command);
    }


}
