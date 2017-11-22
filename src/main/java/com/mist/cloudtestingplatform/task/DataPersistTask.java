package com.mist.cloudtestingplatform.task;

import com.mist.cloudtestingplatform.model.Data;

/**
 * Created by Prophet on 2017/11/22.
 */
public interface DataPersistTask {

    /**
     * Async persist data.
     * Persist data by batch.
     * Persist data when data don't meet the batch size and timeout.
     * @param data
     * @return
     */
    public boolean persistData(Data data);

}
