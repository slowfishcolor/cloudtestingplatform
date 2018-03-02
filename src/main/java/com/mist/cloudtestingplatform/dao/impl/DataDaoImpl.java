package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.DataDao;
import com.mist.cloudtestingplatform.model.Data;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/21.
 */
@Repository
public class DataDaoImpl extends DaoBase implements DataDao {

    @Override
    public void saveData(Data data) {
        currentSession().save(data);
    }

    @Override
    public void saveDataCollection(Collection<Data> datas) {
        int i = 0;
        for(Data data: datas) {
            currentSession().save(data);
            i++;
            // prevent out of memory in hibernate cache
            if (i % 20 == 0) {
                currentSession().flush();
                currentSession().clear();
            }
        }
    }

    @Override
    public Data getData(Integer id) {
        String hql = "from Data where id = :id";
        List<Data> rs = currentSession().createQuery(hql)
                .setInteger("id", id).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public List<Data> getDataList(String deviceId, Integer userId, Integer offset, Integer length) {
        String hql = "from Data where userId = :uId and deviceId = :dId order by timestamp desc";
        return currentSession().createQuery(hql)
                .setInteger("uId", userId).setString("dId", deviceId)
                .setFirstResult(offset).setMaxResults(length).list();
    }

    @Override
    public List<Data> getDataListByDirection(Integer direction, String deviceId, Integer userId, Integer offset, Integer length) {
        String hql = "from Data where userId = :uId and direction = :dir and deviceId = :dId order by timestamp desc";
        return currentSession().createQuery(hql).setInteger("dir", direction)
                .setInteger("uId", userId).setString("dId", deviceId)
                .setFirstResult(offset).setMaxResults(length).list();
    }

    @Override
    public Long getDataListCount(String deviceId, Integer userId) {
        String hql = "select count(*) from Data where userId = :uId and deviceId = :dId";
        return (Long) currentSession().createQuery(hql).
                setInteger("uId", userId).setString("dId", deviceId)
                .uniqueResult();
    }

    @Override
    public Long getDataListCountByDirection(String deviceId, Integer userId) {
        return null;
    }

    @Override
    public Long getDataCount() {
        String hql = "select count (*) from Data";
        return (Long) currentSession().createQuery(hql).uniqueResult();
    }
}
