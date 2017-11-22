package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.Data;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/21.
 */
public interface DataDao {

    public void saveData(Data data);

    public void saveDataCollection(Collection<Data> datas);

    public Data getData(Integer id);

    public List<Data> getDataList(String deviceId, Integer userId, Integer offset, Integer length);

    public List<Data> getDataListByDirection(Integer direction, String deviceId, Integer userId, Integer offset, Integer length);

    public Long getDataListCount(String deviceId, Integer userId);

    public Long getDataListCountByDirection(String deviceId, Integer userId);

}
