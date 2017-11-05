package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.Device;
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
public class DeviceDaoImplTest {

    @Autowired
    DeviceDao deviceDao;

    @Test
    public void getDeviceTest() {
        Device device = deviceDao.getDevice("deviceId");
        System.out.println(device.toString());
    }
}
