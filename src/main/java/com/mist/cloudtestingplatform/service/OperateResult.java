package com.mist.cloudtestingplatform.service;

/**
 * Created by Prophet on 2017/11/15.
 */
public class OperateResult {

    private int code;

    private String message;

    private Object data;

    public OperateResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public OperateResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
