package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.FileDao;
import com.mist.cloudtestingplatform.model.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2018/1/19.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml"})
public class FileDaoImplTest {

    @Autowired
    FileDao fileDao;

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFile() {
        File file = new File();
        file.setName("test.text");
        file.setUpdateTime(System.currentTimeMillis());
        file.setData(new byte[]{0});
        file.setRemark("test");
        fileDao.saveFile(file);
    }
}
