package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.Device;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
@Repository
public class DeviceDaoImpl extends DaoBase implements DeviceDao {

    @Override
    public List<Device> listAllDevice() {
        String hql = "from Device";
        return currentSession().createQuery(hql).list();
    }

    @Override
    public List<Device> listVisibleDevice(Integer userId) {
        String hql = "from Device where ownerId = :uId or visibility > 1";
        return currentSession()
                .createQuery(hql).setInteger("uId", userId).list();
    }

    @Override
    public Device getDevice(String deviceId) {
        String hql = "from Device where deviceId = :dId";
        List<Device> rs = currentSession()
                .createQuery(hql).setString("dId", deviceId).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public void saveDevice(Device device) {
        currentSession().save(device);
    }

    @Override
    public List<Device> listDevice(Collection deviceIds) {
        String hql = "from Device where deviceId in (:deviceIds)";
        return currentSession().createQuery(hql)
                .setParameterList("deviceIds", deviceIds).list();
    }

    @Override
    public void deleteDevice(Collection deviceIds) {
        String hql = "update Device set status = 4 where deviceId in (:deviceIds)";
        currentSession().createQuery(hql)
                .setParameterList("deviceIds", deviceIds).executeUpdate();
    }

    @Override
    public void updateDevice(Device device) {
        currentSession().update(device);
    }

    @Override
    public Long getDeviceCount() {
        String hql = "select count (*) from Device";
        return (Long) currentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public Long getPhysicalDeviceCount() {
        String hql = "select count (*) from Device where virtual = 0";
        return (Long) currentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public Device getNewDevice() {
        String hql = "from Device order by registerTime desc ";
        List<Device> rs = currentSession()
                .createQuery(hql).setFirstResult(0).setMaxResults(1).list();
        return rs.get(0);
    }

}
