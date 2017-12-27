package com.mist.cloudtestingplatform.service;

/**
 * Created by Prophet on 2017/12/22.
 */
public interface MQService {

    public OperateResult sendSingleCommand(String deviceId, String command);

    public OperateResult sendStartStopCommand(String deviceId, boolean isStart);
}
