package com.mist.cloudtestingplatform.protocol.device;

import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.util.PayloadUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prophet on 2017/12/27.
 */
public class ProtocolDevicePCIe6320 extends AbstractProtocolDevice {



    public static Payload createSetupCommandPayload(String deviceId, String physicalDeviceId, String channel, String method, float minVoltage, float maxVoltage, int samples, float rate, String command) {

        Map<String, String> commandMap = new HashMap<>();

        commandMap.put("channel", channel);
        commandMap.put("method", method);
        commandMap.put("minVoltage", String.valueOf(minVoltage));
        commandMap.put("maxVoltage", String.valueOf(maxVoltage));
        commandMap.put("samples", String.valueOf(samples));
        commandMap.put("rate", String.valueOf(rate));

        if (COMMAND_START.equals(command) || COMMAND_STOP.equals(command)) {
            commandMap.put("command", command);
        } else {
            commandMap.put("command", COMMAND_SETUP);
        }

        return PayloadUtil.createControlDataWithMapCommand(deviceId, physicalDeviceId, commandMap);
    }
}
