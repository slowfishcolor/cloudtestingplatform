package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.exception.BusinessException;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.MappingModel;
import com.mist.cloudtestingplatform.model.Model;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.DeviceService;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import com.mist.cloudtestingplatform.util.IdUtils;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Prophet on 2017/11/5.
 */
@Transactional
@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceDao deviceDao;

    private ModelDao modelDao;

    private UserDao userDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Autowired
    public void setModelDao(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
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

    @NotNull
    @Override
    public List<Device> listVisibleDeviceByUser(Integer userId) {
        List<Device> result;
        result = deviceDao.listVisibleDevice(userId);
        if (result == null || result.size() == 0) {
            result = new ArrayList<>();
        } else {

            Set<Integer> modelIdSet = new HashSet<>();
            Set<Integer> userIdSet = new HashSet<>();

            for (Device device: result) {
                modelIdSet.add(device.getModelId());
                userIdSet.add(device.getOwnerId());
            }

            // fill model
            List<Model> modelList = modelDao.listModelIn(modelIdSet);
            fillModel(result, modelList);

            // fill user
            List<User> userList = userDao.getUserIn(userIdSet);
            fillUser(result, userList);
        }
        return result;
    }

    @Override
    public OperateResult deleteDevice(String deviceIdStrs, Integer userId) {
        List<String> deviceIds = IdUtils.stringIdsFromStr(deviceIdStrs);
        List<Device> deviceList = deviceDao.listDevice(deviceIds);
        for (Device device: deviceList) {
            if (device.getOwnerId() != userId) {
                throw new BusinessException(OperateResultFactory.failResult("只能删除用户自己的设备！"));
            }
        }
        deviceDao.deleteDevice(deviceIds);
        return OperateResultFactory.successResult();
    }

    @Override
    public OperateResult updateDeviceConfig(String deviceId, String config) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) {
            return OperateResultFactory.failResult("invalid deviceId");
        }
        device.setConfig(config);
        deviceDao.updateDevice(device);
        return OperateResultFactory.successResult();
    }

    @Override
    public MappingModel getDeviceMapping(String deviceId) {
        Device device = deviceDao.getDevice(deviceId);
        if (device == null) return null;

        MappingModel mappingModel = new MappingModel();

        List<Device> deviceList = deviceDao.listAllDevice();
        if (device.getVirtual() == 0) {
            // physical device
            return mappingFromDeviceId(deviceId, deviceList);
        } else if (device.getVirtual() == 1) {
            // virtual device
            return mappingFromDeviceId(device.getPhysicalDeviceId(), deviceList);
        }
        return null;
    }

    private MappingModel mappingFromDeviceId(String physicalDeviceId, List<Device> deviceList) {
        MappingModel mappingModel = new MappingModel();
        mappingModel.setName(physicalDeviceId);
        for (Device device: deviceList) {
            if (device.getVirtual() == 1 && physicalDeviceId.equals(device.getPhysicalDeviceId())) {
                mappingModel.getChildren().add(new MappingModel.Child(device.getDeviceId()));
            }
        }
        return mappingModel;
    }

    private void fillModel(List<Device> deviceList, List<Model> modelList) {
        Map<Integer, Model> modelMap = new HashMap<>();
        for (Model model: modelList) {
            modelMap.put(model.getId(), model);
        }
        for (Device device: deviceList) {
            device.setModel(modelMap.get(device.getModelId()));
        }
    }

    private void fillUser(List<Device> deviceList, List<User> userList) {
        Map<Integer, User> userMap = new HashMap<>();
        for (User user: userList) {
            userMap.put(user.getId(), user);
        }
        for (Device device: deviceList) {
            device.setOwner(userMap.get(device.getOwnerId()));
        }
    }


}
