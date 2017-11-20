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

    public OperateResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public OperateResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public OperateResult setData(Object data) {
        this.data = data;
        return this;
    }
}
