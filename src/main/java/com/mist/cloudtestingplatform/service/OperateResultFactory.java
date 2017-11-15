package com.mist.cloudtestingplatform.service;

import java.util.Objects;

/**
 * Created by Prophet on 2017/11/15.
 */
public class OperateResultFactory {

    public static OperateResult newResult(int code, String message, Object data) {
        return new OperateResult(code, message, data);
    }

    public static OperateResult successResult() {
        return new OperateResult(200, "success");
    }

    public static OperateResult operateResult(Object data) {
        return newResult(200, "success", data);
    }

    public static OperateResult failResult() {
        return new OperateResult(400, "fail");
    }

    public static OperateResult failResult(String message) {
        return new OperateResult(400, message);
    }
}
