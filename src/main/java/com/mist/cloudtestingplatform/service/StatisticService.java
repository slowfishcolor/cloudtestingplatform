package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.Task;

import java.util.Map;

/**
 * Created by Prophet on 2018/3/2.
 */
public interface StatisticService {

    public Map<String, String> listStatisticCount();

    public Device getNewDevice();

    public Task getNewTask(Integer uesrId);
}
