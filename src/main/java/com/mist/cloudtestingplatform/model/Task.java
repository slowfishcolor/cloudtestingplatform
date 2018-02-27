package com.mist.cloudtestingplatform.model;

import com.mist.cloudtestingplatform.util.TimeUtils;

/**
 * Created by Prophet on 2016/12/20.
 */
public class Task extends BaseModel {

    private String taskName;

    private String script;

    private long messageId;

    private long timestamp;

    private int status;

    private String result;

    private int userId;

    private String deviceId;

    public String getTimeStr() {
        return TimeUtils.getDateTimeFromTimestamp(timestamp);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
