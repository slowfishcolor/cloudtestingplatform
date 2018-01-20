package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.File;

import java.util.List;

/**
 * Created by Prophet on 2018/1/19.
 */
public interface FileDao {

    public File getFileByName(String fileName);

    public void saveFile(File file);

    public List<File> listAllFilesWithoutData();
}
