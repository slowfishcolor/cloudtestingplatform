package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.exception.BusinessException;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Prophet on 2017/11/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public OperateResult handlerException(Exception e) {
        return OperateResultFactory.failResult();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public OperateResult handleBusinessException(BusinessException e) {
        return e.getOperateResult();
    }
}
