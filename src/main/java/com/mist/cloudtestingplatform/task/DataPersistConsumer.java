package com.mist.cloudtestingplatform.task;

/**
 * Created by Prophet on 2017/11/23.
 */
public abstract class DataPersistConsumer implements Runnable {

    protected volatile boolean isRunning = true;

    public void stop() {
        this.isRunning = false;
    }
}
