package com.mist.cloudtestingplatform.service;

/**
 * Created by Prophet on 2017/12/27.
 */
public interface MQServicePCIe6320 extends MQService {

    public OperateResult sendSetupCommand(String deviceId, String channel, String method, float minVoltage, float maxVoltage, int samples, float rate, String command, int userId);

}
