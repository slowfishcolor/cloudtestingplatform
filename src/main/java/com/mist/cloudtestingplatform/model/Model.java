package com.mist.cloudtestingplatform.model;

import com.mist.cloudtestingplatform.util.TimeUtils;

/**
 * Created by Prophet on 2017/11/4.
 */
public class Model extends BaseModel {

    private int id;

    private String name;

    private int type;

    private int testType;

    private int status;

    private long registerTime;

    private String config;

    private String remark;

    public String getTypeStr() {

        switch (type) {
            case 0:
                return "测试设备";
            case 1:
                return "UUT";
            default:
                return "未知设备";
        }

    }

    public String getTestTypeStr() {

        switch (testType) {
            case 0:
                return "数字设备";
            case 1:
                return "模拟设备";
            case 2:
                return "数字 + 模拟";
            default:
                return "未知设备";
        }

    }

    public String getStatusStr() {

        switch (status) {
            case 0:
                return "正常";
            case 1:
                return "删除";
            default:
                return "未知";
        }

    }

    public String getRegisterTimeStr() {
        return TimeUtils.getDateTimeFromTimestamp(registerTime);
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
