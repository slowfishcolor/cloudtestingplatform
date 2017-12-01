package com.mist.cloudtestingplatform.protocol.model;

/**
 * Created by Prophet on 2017/12/1.
 */
public class ControlData extends Data {

    private String instruction;

    public ControlData() {
        super.type = "ControlData";
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
