package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.mq.ServerMessageSender;
import com.mist.cloudtestingplatform.protocol.model.Instruction;
import com.mist.cloudtestingplatform.protocol.model.InstructionData;
import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.util.PayloadUtil;
import com.mist.cloudtestingplatform.service.MQServiceSmartGateway;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2018/2/26.
 */
@Transactional
@Service
public class MQServiceSmartGatewayImpl implements MQServiceSmartGateway {

    private ServerMessageSender messageSender;

    private DeviceDao deviceDao;

    @Autowired
    public void setMessageSender(ServerMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public OperateResult sendSingleInstruction(String deviceId, int userId, Instruction instruction) {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(instruction);
        return sendInstructions(deviceId, userId, instructions);
    }

    @Override
    public OperateResult sendInstructions(String deviceId, int userId, List<Instruction> instructions) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) {
            return OperateResultFactory.failResult("invalid deviceId");
        }
        InstructionData instructionData = new InstructionData(instructions);
        Payload payload = PayloadUtil.createInstructionPayload(deviceId, device.getPhysicalDeviceId(), userId, instructionData);
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }

}
