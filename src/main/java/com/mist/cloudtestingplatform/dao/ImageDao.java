package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.Image;

/**
 * Created by Prophet on 2017/11/18.
 */
public interface ImageDao {

    public Image getImage(Integer id);

    public void saveOrUpdateImage(Image image);

}
