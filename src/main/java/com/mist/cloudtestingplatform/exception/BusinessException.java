package com.mist.cloudtestingplatform.exception;

import com.mist.cloudtestingplatform.service.OperateResult;

/**
 * Created by Prophet on 2017/11/15.
 */
public class BusinessException extends RuntimeException {

    private OperateResult operateResult;

    public OperateResult getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(OperateResult operateResult) {
        this.operateResult = operateResult;
    }

    public BusinessException(OperateResult operateResult) {
        super();
        this.operateResult = operateResult;
    }

}
