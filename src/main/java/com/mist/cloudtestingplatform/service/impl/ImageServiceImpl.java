package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.ImageDao;
import com.mist.cloudtestingplatform.model.Image;
import com.mist.cloudtestingplatform.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Prophet on 2017/11/18.
 */
@Transactional
@Service
public class ImageServiceImpl implements ImageService{

    private ImageDao imageDao;

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public Integer saveImage(byte[] imageBytes) {
        Image image = new Image();
        image.setData(imageBytes);
        image.setUpdateTime(System.currentTimeMillis());
        imageDao.saveOrUpdateImage(image);
        return image.getId();
    }

    @Override
    public Integer saveImage(Image image) {
        imageDao.saveOrUpdateImage(image);
        return image.getId();
    }

    @Override
    public Image getImage(Integer imageId) {
        return imageDao.getImage(imageId);
    }
}
