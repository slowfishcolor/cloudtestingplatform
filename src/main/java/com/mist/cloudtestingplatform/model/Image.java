package com.mist.cloudtestingplatform.model;

/**
 * Created by Prophet on 2017/11/18.
 */
public class Image extends BaseModel {

    private int id;

    private long updateTime;

    private byte[] data;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
