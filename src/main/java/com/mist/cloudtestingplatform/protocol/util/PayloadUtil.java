package com.mist.cloudtestingplatform.protocol.util;

import com.mist.cloudtestingplatform.protocol.model.ControlData;
import com.mist.cloudtestingplatform.protocol.model.Option;
import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.model.PayloadFactory;

import java.util.Map;

/**
 * Created by Prophet on 2017/12/22.
 */
public class PayloadUtil {

    public static Payload createControlDataWithSingleCommand(String deviceId, String physicalDeviceId, int userId, String command) {

        Option option = new Option();
        ControlData controlData = new ControlData();

        option.setPublish(1);
        option.setSubscribe(0);

        controlData.setCommandCount(1);
        controlData.setCommand(command);

        Payload payload = PayloadFactory.createPayload(option, controlData);
        payload.setDeviceId(deviceId);
        payload.setPhysicalDeviceId(physicalDeviceId);
        payload.setUserId(userId);

        return payload;
    }

    public static Payload createControlDataWithMapCommand(String deviceId, String physicalDeviceId, Map<String, String> commandMap, int userId) {

        Option option = new Option();
        ControlData controlData = new ControlData();

        option.setPublish(1);
        option.setSubscribe(0);

        controlData.setCommandCount(commandMap.size());
        controlData.setCommandMap(commandMap);

        Payload payload = PayloadFactory.createPayload(option, controlData);
        payload.setDeviceId(deviceId);
        payload.setPhysicalDeviceId(physicalDeviceId);
        payload.setUserId(userId);

        return payload;
    }
}
