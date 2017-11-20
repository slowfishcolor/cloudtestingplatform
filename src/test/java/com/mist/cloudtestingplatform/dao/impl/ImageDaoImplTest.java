package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.ImageDao;
import com.mist.cloudtestingplatform.model.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2017/11/18.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml"})
public class ImageDaoImplTest {

    @Autowired
    ImageDao imageDao;

    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateTest() {
        Image image = new Image();
        image.setUpdateTime(System.currentTimeMillis());
        byte[] bytes = {1};
        image.setData(bytes);
        imageDao.saveOrUpdateImage(image);
        Image image1 = imageDao.getImage(1);
        System.out.println(image);
    }

    @Test
    public void getImageTest() {
        Image image = imageDao.getImage(1);
        System.out.println("" + image);
    }



}
