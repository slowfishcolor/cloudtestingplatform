package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.annotation.DeviceAuth;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.protocol.model.Instruction;
import com.mist.cloudtestingplatform.service.MQServiceSmartGateway;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Prophet on 2018/2/26.
 */
@Auth
@DeviceAuth
@SessionAttributes("user")
@Controller
public class MqControllerSmartGateway implements MqController {

    private static final Logger logger = LoggerFactory.getLogger(MqControllerSmartGateway.class);

    private MQServiceSmartGateway mqServiceSmartGateway;

    @Autowired
    public void setMqServiceSmartGateway(MQServiceSmartGateway mqServiceSmartGateway) {
        this.mqServiceSmartGateway = mqServiceSmartGateway;
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/mq/smartGateway/sendSingleInstruction", method = RequestMethod.POST)
    public OperateResult sendSingleInstruction(String deviceId, String instructionString, String port, @ModelAttribute User user) {

        logger.info("deviceId: {}, instruction: {}", deviceId, instructionString);
        Instruction instruction = new Instruction();
        instruction.setType(Instruction.COMMAND_STRING);
        instruction.setName(instructionString);
        instruction.setPort(port);

        long messageId = IdUtils.generateMessageId();

        return mqServiceSmartGateway.sendSingleInstruction(deviceId, user.getId(), messageId, instruction);
    }
}
