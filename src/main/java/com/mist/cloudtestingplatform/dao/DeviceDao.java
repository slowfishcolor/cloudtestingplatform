package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.Device;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/4.
 */
public interface DeviceDao {

    public List<Device> listAllDevice();

    public List<Device> listVisibleDevice(Integer userId);

    public Device getDevice(String deviceId);

    public void saveDevice(Device device);

    public List<Device> listDevice(Collection deviceIds);

    public void deleteDevice(Collection deviceIds);

    public void updateDevice(Device device);

    public Long getDeviceCount();

    public Long getPhysicalDeviceCount();

    public Device getNewDevice();
}
