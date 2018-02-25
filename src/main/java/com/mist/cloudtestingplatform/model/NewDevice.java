package com.mist.cloudtestingplatform.model;

/**
 * Created by Prophet on 2018/2/24.
 */
public class NewDevice extends BaseModel {

    private String deviceId = "";
    private boolean virtual = false;
    private String baseDeviceId = "";
    private int baseModelId = 1;
    private String physicalDeviceId = "";
    private String remark = "";
    private int visibility = 0;
    private String fileName = "";

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public String getBaseDeviceId() {
        return baseDeviceId;
    }

    public void setBaseDeviceId(String baseDeviceId) {
        this.baseDeviceId = baseDeviceId;
    }

    public int getBaseModelId() {
        return baseModelId;
    }

    public void setBaseModelId(int baseModelId) {
        this.baseModelId = baseModelId;
    }

    public String getPhysicalDeviceId() {
        return physicalDeviceId;
    }

    public void setPhysicalDeviceId(String physicalDeviceId) {
        this.physicalDeviceId = physicalDeviceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
