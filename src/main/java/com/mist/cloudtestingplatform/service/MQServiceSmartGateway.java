package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.protocol.model.Instruction;

import java.util.List;

/**
 * Created by Prophet on 2018/2/26.
 */
public interface MQServiceSmartGateway {

    public OperateResult sendSingleInstruction(String deviceId, int userId, Instruction instruction);

    public OperateResult sendInstructions(String deviceId, int userId, List<Instruction> instructions);
}
