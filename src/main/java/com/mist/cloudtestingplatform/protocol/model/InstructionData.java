package com.mist.cloudtestingplatform.protocol.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2018/1/5.
 */
public class InstructionData extends Data {

    private int instructionCount = 0;

    private List<Instruction> instructions;

    public InstructionData() {
        this.instructionCount = 0;
        this.instructions = new ArrayList<>();
        super.type = "InstructionData";
    }

    public InstructionData(List<Instruction> instructions) {
        instructionCount = instructions.size();
        this.instructions = instructions;
        super.type = "InstructionData";
    }

    public InstructionData(Instruction instruction) {
        instructionCount = 1;
        this.instructions = new ArrayList<>();
        this.instructions.add(instruction);
        super.type = "InstructionData";
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
