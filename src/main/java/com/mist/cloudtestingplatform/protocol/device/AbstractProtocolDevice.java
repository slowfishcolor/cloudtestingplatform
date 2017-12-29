package com.mist.cloudtestingplatform.protocol.device;

import com.mist.cloudtestingplatform.protocol.model.Payload;
import com.mist.cloudtestingplatform.protocol.util.PayloadUtil;

/**
 * Created by Prophet on 2017/12/27.
 */
public abstract class AbstractProtocolDevice implements ProtocolDevice {

    public static final String COMMAND_START = "start";

    public static final String COMMAND_STOP = "stop";

    public static final String COMMAND_SETUP = "setup";

    public static Payload createSingleCommandPayload(String deviceId, String physicalDeviceId, String command) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, physicalDeviceId, command);
    }

    public static Payload createStartCommandPayload(String deviceId, String physicalDeviceId) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, physicalDeviceId, COMMAND_START);
    }

    public static Payload createStopCommandPayload(String deviceId, String physicalDeviceId) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, physicalDeviceId, COMMAND_STOP);
    }
}
