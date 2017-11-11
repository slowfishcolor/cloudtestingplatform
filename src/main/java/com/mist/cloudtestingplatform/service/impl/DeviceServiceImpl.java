package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.model.Model;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.service.DeviceService;
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
        if (result == null) {
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
