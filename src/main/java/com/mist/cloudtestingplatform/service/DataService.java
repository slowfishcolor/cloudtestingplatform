package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Data;
import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/22.
 */
public interface DataService {

    public void saveData(Data data);

    public void saveDataCollection(Collection<Data> datas);

    @NotNull
    public List<Data> listDataByPage(String deviceId, Integer userId, int page, int pageSize);

    public Integer listDataCount(String deviceId, Integer userId);

}
