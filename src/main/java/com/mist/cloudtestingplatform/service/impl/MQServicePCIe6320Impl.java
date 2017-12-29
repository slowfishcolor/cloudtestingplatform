package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.Device;
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
    public OperateResult sendSingleCommand(String deviceId, int userId, String command) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) {
            return OperateResultFactory.failResult("invalid deviceId");
        }
        Payload payload = PayloadUtil.createControlDataWithSingleCommand(deviceId, device.getPhysicalDeviceId(), userId, command);
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }


    @Override
    public OperateResult sendStartStopCommand(String deviceId, int userId, boolean isStart) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) {
            return OperateResultFactory.failResult("invalid deviceId");
        }
        Payload payload = null;
        if (isStart) {
            payload = ProtocolDevicePCIe6320.createStartCommandPayload(deviceId, device.getPhysicalDeviceId(), userId);
        } else {
            payload = ProtocolDevicePCIe6320.createStopCommandPayload(deviceId, device.getPhysicalDeviceId(), userId);
        }
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }

    @Override
    public OperateResult sendSetupCommand(String deviceId, String channel, String method, float minVoltage, float maxVoltage, int samples, float rate, String command, int userId) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) {
            return OperateResultFactory.failResult("invalid deviceId");
        }
        Payload payload = null;
        payload = ProtocolDevicePCIe6320.createSetupCommandPayload(deviceId, device.getPhysicalDeviceId(), channel, method, minVoltage, maxVoltage, samples, rate, command, userId);
        if (messageSender.sendMessage(payload)) {
            return OperateResultFactory.successResult();
        }
        return OperateResultFactory.failResult();
    }
}
