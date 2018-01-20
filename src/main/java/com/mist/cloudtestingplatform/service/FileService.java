package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.File;

import java.util.List;

/**
 * Created by Prophet on 2018/1/19.
 */
public interface FileService {

    public void saveFile(File file);

    public File getFileByName(String fileName);

    public List<File> listAllFilesWithoutData();
}
