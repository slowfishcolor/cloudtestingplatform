package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.BaseModel;
import com.mist.cloudtestingplatform.model.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DeviceDaoImplTest {

    @Autowired
    DeviceDao deviceDao;

    @Test
    public void getDeviceTest() {
        Device device = deviceDao.getDevice("deviceId");
        System.out.println(device.toString());
    }

    @Test
    public void listVisibleDeviceTest() {
        List<Device> devices = deviceDao.listVisibleDevice(1);
        System.out.println(devices.size());
        for (Device device: devices) {
            System.out.println(device.getDeviceId());
        }
    }

    @Test
    public void basicTest() {
        Device device = new Device();
        device.setId(1);
        BaseModel baseModel = device;
        System.out.println(baseModel.getId());
        baseModel.setId(2);
        System.out.println(device.getId());
    }

    @Test
    public void updateDeviceTest() {
        Device device = deviceDao.getDevice("virtualDeviceId");
        device.setVirtual(0);
        deviceDao.updateDevice(device);
        device = deviceDao.getDevice("virtualDeviceId");
        System.out.println(device.getVirtual());
    }

    @Test
    public void saveDeviceTest() {
//        Device device = deviceDao.getDevice("virtualDeviceId");
        Device device1 = new Device();
//        device1.setVirtual(device.getVirtual());
//        device1.setConfig(device.getConfig());
//        device1.setDeviceId(device.getConfig() + "-new");
//        device1.setModelId(device.getModelId());
//        device1.setOwnerId(device.getOwnerId());

        device1.setDeviceId("new");
        device1.setModelId(1);
        device1.setStatus(0);
        device1.setOwnerId(1);
        device1.setVisibility(0);
        device1.setVirtual(0);
        deviceDao.saveDevice(device1);
//        Device device2 = deviceDao.getDevice(device.getConfig() + "-new");
//        Device device2 = deviceDao.getDevice("new");
//        System.out.println(device2.getDeviceId());
    }
}
