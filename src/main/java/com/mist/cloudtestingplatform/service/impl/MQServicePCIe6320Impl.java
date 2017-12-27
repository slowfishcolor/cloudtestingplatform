package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.mq.ServerMessageSender;
import com.mist.cloudtestingplatform.protocol.device.ProtocolDevicePCIe6320;
import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.util.PayloadUtil;
import com.mist.cloudtestingplatform.service.MQServicePCIe6320;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2017/12/22.
 */
@Transactional
@Service
public class MQServicePCIe6320Impl implements MQServicePCIe6320 {

    private ServerMessageSender messageSender;

    @Autowired
    public void setMessageSender(ServerMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public OperateResult sendSingleCommand(String deviceId, String command) {
        Payload payload = PayloadUtil.createControlDataWithSingleCommand(deviceId, command);
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }


    @Override
    public OperateResult sendStartStopCommand(String deviceId, boolean isStart) {
        Payload payload = null;
        if (isStart) {
            payload = ProtocolDevicePCIe6320.createStartCommandPayload(deviceId);
        } else {
            payload = ProtocolDevicePCIe6320.createStopCommandPayload(deviceId);
        }
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }

    @Override
    public OperateResult sendSetupCommand(String deviceId, String channel, String method, float minVoltage, float maxVoltage, int samples, float rate, String command) {
        Payload payload = null;
        payload = ProtocolDevicePCIe6320.ceateSetupCommandPayload(deviceId, channel, method, minVoltage, maxVoltage, samples, rate, command);
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }
}
