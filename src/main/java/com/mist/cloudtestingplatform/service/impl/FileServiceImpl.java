package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.FileDao;
import com.mist.cloudtestingplatform.model.File;
import com.mist.cloudtestingplatform.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prophet on 2018/1/19.
 */
@Transactional
@Service
public class FileServiceImpl implements FileService{

    private FileDao fileDao;

    @Autowired
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public void saveFile(File file) {
        fileDao.saveFile(file);
    }

    @Override
    public File getFileByName(String fileName) {
        return fileDao.getFileByName(fileName);
    }

    @Override
    public List<File> listAllFilesWithoutData() {
        return fileDao.listAllFilesWithoutData();
    }
}
