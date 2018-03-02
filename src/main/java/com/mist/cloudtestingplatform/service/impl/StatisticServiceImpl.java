package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DataDao;
import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.dao.TaskDao;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.Task;
import com.mist.cloudtestingplatform.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prophet on 2018/3/2.
 */
@Transactional
@Service
public class StatisticServiceImpl implements StatisticService {

    private DeviceDao deviceDao;

    private ModelDao modelDao;

    private DataDao dataDao;

    private TaskDao taskDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Autowired
    public void setModelDao(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Autowired
    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Map<String, String> listStatisticCount() {
        long deviceCount = deviceDao.getDeviceCount();
        long physicalDeviceCount = deviceDao.getPhysicalDeviceCount();
        long virtualDeviceCount = deviceCount - physicalDeviceCount;
        long modelCount = modelDao.getModelCount();
        long dataCount = dataDao.getDataCount();
        long taskCount = taskDao.getTaskCount();
        Map<String, String> map = new HashMap<>();
        map.put("deviceCount", String.valueOf(deviceCount));
        map.put("physicalDeviceCount", String.valueOf(physicalDeviceCount));
        map.put("virtualDeviceCount", String.valueOf(virtualDeviceCount));
        map.put("modelCount", String.valueOf(modelCount));
        map.put("dataCount", String.valueOf(dataCount));
        map.put("taskCount", String.valueOf(taskCount));
        return map;
    }

    @Override
    public Device getNewDevice() {
        Device device = deviceDao.getNewDevice();
        device.setModel(modelDao.getModel(device.getModelId()));
        return device;
    }

    @Override
    public Task getNewTask(Integer userId) {
        return taskDao.getNewTask(userId);
    }
}
