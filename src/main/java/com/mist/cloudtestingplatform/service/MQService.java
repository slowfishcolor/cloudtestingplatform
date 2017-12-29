package com.mist.cloudtestingplatform.service;

/**
 * Created by Prophet on 2017/12/22.
 */
public interface MQService {

    public OperateResult sendSingleCommand(String deviceId, int userId, String command);

    public OperateResult sendStartStopCommand(String deviceId, int userId, boolean isStart);
}
