package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.MappingModel;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
public interface DeviceService {

    public Device getDeviceByDeviceId(String deviceId);

    @NotNull
    public List<Device> listVisibleDeviceByUser(Integer userId);

    public OperateResult deleteDevice(String deviceIdStrs, Integer userId);

    public OperateResult updateDeviceConfig(String deviceId, String config);

    public MappingModel getDeviceMapping(String deviceId);

}
