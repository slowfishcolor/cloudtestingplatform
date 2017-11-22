package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Data;

import java.util.Collection;

/**
 * Created by Prophet on 2017/11/22.
 */
public interface DataService {

    public void saveData(Data data);

    public void saveDataCollection(Collection<Data> datas);

}
