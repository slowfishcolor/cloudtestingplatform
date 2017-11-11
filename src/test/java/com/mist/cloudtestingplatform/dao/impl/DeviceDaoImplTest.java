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
}
