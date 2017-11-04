package com.mist.cloudtestingplatform.model;

/**
 * Created by Prophet on 2016/12/20.
 */
public class Device extends BaseModel {

    private int id;

    private String deviceId;

    private String modelId;

    private int status;

    private long registerTime;

    private long updateTime;

    private String config;

    private String remark;

    public String getStatusStr() {

        switch (status) {
            case 0:
                return "正常在线";
            case 1:
                return "正常离线";
            case 2:
                return "使用中";
            case 3:
                return "维护中";
            case 4:
                return "已删除";
            default:
                return "未知";
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
