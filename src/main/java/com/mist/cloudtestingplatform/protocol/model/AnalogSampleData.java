package com.mist.cloudtestingplatform.protocol.model;

/**
 * Created by Prophet on 2017/11/22.
 */
public class AnalogSampleData extends Data {

    private String type = "AnalogSampleData";

    // 设备端口号
    private String port;
    // differential or what
    private String method;

    private double frequency;
    // count of samples
    private int sampleCount;

    // time stamp for this data
    private long timestamp;
    // 具体的值
    private double[] value;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }
}
