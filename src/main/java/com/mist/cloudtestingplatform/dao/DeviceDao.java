package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.Device;

import java.util.List;

/**
 * Created by Prophet on 2017/11/4.
 */
public interface DeviceDao {

    public List<Device> listAllDevice();

    public List<Device> listVisibleDevice(Integer userId);

    public Device getDevice(String deviceId);

    public void saveDevice(Device device);

}
