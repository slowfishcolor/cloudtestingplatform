package com.mist.cloudtestingplatform.protocol.model;

/**
 * Created by Prophet on 2017/11/22.
 */
public class Payload extends PayloadBase{

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
