package com.mist.cloudtestingplatform.protocol.model;

/**
 * Created by Prophet on 2017/11/23.
 */
public class PayloadBase {

    public static String ANALOG_SAMPLE_DATA = "AnalogSampleData";

    public static String CONTROL_DATA = "ControlData";

    public static String INSTRUCTION_DATA = "InstructionData";

    private long messageId;

    private String destination;

    private String deviceId;

    private String physicalDeviceId;

    private long timestamp;

    private int userId;

    private int code;
    // Data 的 type
    private String type;

    private Option option;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhysicalDeviceId() {
        return physicalDeviceId;
    }

    public void setPhysicalDeviceId(String physicalDeviceId) {
        this.physicalDeviceId = physicalDeviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
