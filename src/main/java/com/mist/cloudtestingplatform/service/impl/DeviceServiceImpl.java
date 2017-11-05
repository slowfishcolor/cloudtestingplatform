package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2017/11/5.
 */
@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceDao deviceDao;

    private ModelDao modelDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Autowired
    public void setModelDao(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public Device getDeviceByDeviceId(String deviceId) {
        Device device = deviceDao.getDevice(deviceId);
        if (device != null) {
            device.setModel(modelDao.getModel(device.getModelId()));
            return device;
        }
        return null;
    }
}
