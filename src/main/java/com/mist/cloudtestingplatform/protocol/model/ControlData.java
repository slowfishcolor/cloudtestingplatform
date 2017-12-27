package com.mist.cloudtestingplatform.protocol.model;

import java.util.Map;

/**
 * Created by Prophet on 2017/12/1.
 */
public class ControlData extends Data {

    private int commandCount = 1;

    private String command = "null";

    private String[] commands = null;

    private Map<String, String> commandMap = null;

    public ControlData() {
        super.type = "ControlData";
    }

    public int getCommandCount() {
        return commandCount;
    }

    public void setCommandCount(int commandCount) {
        this.commandCount = commandCount;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public Map<String, String> getCommandMap() {
        return commandMap;
    }

    public void setCommandMap(Map<String, String> commandMap) {
        this.commandMap = commandMap;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
