package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DataDao;
import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Prophet on 2017/11/22.
 */
@Transactional
@Service
public class DataServiceImpl implements DataService{

    private DataDao dataDao;

    @Autowired
    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    @Override
    public void saveData(Data data) {
        dataDao.saveData(data);
    }

    @Override
    public void saveDataCollection(Collection<Data> datas) {
        dataDao.saveDataCollection(datas);
    }
}
