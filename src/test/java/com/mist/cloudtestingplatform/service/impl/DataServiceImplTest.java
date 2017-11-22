package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prophet on 2017/11/22.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml"})
public class DataServiceImplTest {

    @Autowired
    DataService dataService;

    @Test
    public void saveDataListTest() {
        List<Data> dataList = new ArrayList<>();
        Data data = new Data();
        data.setUserId(1);
        data.setDeviceId("deviceId");
        data.setDirection(0);
        data.setTimestamp(0L);
        data.setData("{test}");
        dataList.add(data);
        dataService.saveDataCollection(dataList);
    }
}
