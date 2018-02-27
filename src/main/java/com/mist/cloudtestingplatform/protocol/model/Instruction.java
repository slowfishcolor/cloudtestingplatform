package com.mist.cloudtestingplatform.protocol.model;

import java.util.Map;

/**
 * Created by Prophet on 2018/1/5.
 */
public class Instruction {

    public static String COMMAND_STRING = "COMMAND_STRING";
    public static String COMMAND_NUMBER = "COMMAND_NUMBER";
    public static String RESULT_STRING = "RESULT_STRING";
    public static String RESULT_NUMBER = "RESULT_NUMBER";

    private String name = "";
    private String type = COMMAND_STRING;
    private String valueString = "";
    private double valueNumber = 0.0;
    private double minValue = 0.0;
    private double maxValue = 0.0;
    private int interval = 100;
    private long timestamp = 0;
    private String remark = "";
    private String port = "/dev/ttyUSB0";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public double getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(double valueNumber) {
        this.valueNumber = valueNumber;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
