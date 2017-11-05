package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.model.Device;
import com.mist.cloudtestingplatform.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2017/11/5.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DeviceServiceImplTest {

    @Autowired
    DeviceService deviceService;

    @Test
    public void getDeviceByIdTest() {
        Device device = deviceService.getDeviceByDeviceId("deviceId");
        System.out.println(device.toString());
    }


}
