package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Image;

/**
 * Created by Prophet on 2017/11/18.
 */
public interface ImageService {

    public Integer saveImage(byte[] imageBytes);

    public Integer saveImage(Image image);

    public Image getImage(Integer imageId);

}
