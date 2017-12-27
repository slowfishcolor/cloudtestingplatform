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

    public static Payload createSingleCommandPayload(String deviceId, String command) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, command);
    }

    public static Payload createStartCommandPayload(String deviceId) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, COMMAND_START);
    }

    public static Payload createStopCommandPayload(String deviceId) {
        return PayloadUtil.createControlDataWithSingleCommand(deviceId, COMMAND_STOP);
    }
}
