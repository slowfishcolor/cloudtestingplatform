package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.DataDao;
import com.mist.cloudtestingplatform.model.Data;
import com.mist.cloudtestingplatform.service.DataService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @NotNull
    @Override
    public List<Data> listDataByPage(String deviceId, Integer userId, int page, int pageSize) {
        List<Data> datas = dataDao.getDataList(deviceId, userId, (page - 1) * pageSize, pageSize);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        return datas;
    }

    @Override
    public Integer listDataCount(String deviceId, Integer userId) {
        return dataDao.getDataListCount(deviceId, userId).intValue();
    }
}
