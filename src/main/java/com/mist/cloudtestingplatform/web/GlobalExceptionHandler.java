package com.mist.cloudtestingplatform.web;

import com.mist.cloudtestingplatform.exception.BusinessException;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Prophet on 2017/11/15.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public OperateResult handlerException(Exception e) {
        logger.warn(e.toString());
        e.printStackTrace();
        return OperateResultFactory.failResult();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public OperateResult handleBusinessException(BusinessException e) {
        logger.warn(e.toString());
        e.printStackTrace();
        return e.getOperateResult();
    }
}
