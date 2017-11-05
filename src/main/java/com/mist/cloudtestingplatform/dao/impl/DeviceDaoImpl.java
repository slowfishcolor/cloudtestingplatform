package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.DeviceDao;
import com.mist.cloudtestingplatform.model.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
@Repository
public class DeviceDaoImpl extends DaoBase implements DeviceDao {

    @Override
    public List<Device> listAllDevice() {
        String hql = "from Device";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Device> listVisibleDevice(Integer userId) {
        return null;
    }

    @Override
    public Device getDevice(String deviceId) {
        String hql = "from Device where deviceId = :dId";
        List<Device> rs = sessionFactory.getCurrentSession()
                .createQuery(hql).setString("dId", deviceId).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public void saveDevice(Device device) {
        sessionFactory.getCurrentSession().save(device);
    }

}
