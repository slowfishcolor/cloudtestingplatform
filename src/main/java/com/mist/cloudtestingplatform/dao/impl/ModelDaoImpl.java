package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.ModelDao;
import com.mist.cloudtestingplatform.model.Model;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2017/11/5.
 */
@Repository
public class ModelDaoImpl extends DaoBase implements ModelDao {
    @Override
    public List<Model> listAllModel() {
        return null;
    }

    @Override
    public Model getModel(Integer modelId) {
        String hql = "from Model where id = :mId";
        List<Model> rs = sessionFactory.getCurrentSession()
                .createQuery(hql).setInteger("mId", modelId).list();
        if (rs != null && rs.size() > 0 ) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public List<Model> listModelIn(Collection<Integer> idList) {
        String hql = "from Model where id in (:idList)";
        return sessionFactory.getCurrentSession()
                .createQuery(hql).setParameterList("idList", idList)
                .list();
    }
}
