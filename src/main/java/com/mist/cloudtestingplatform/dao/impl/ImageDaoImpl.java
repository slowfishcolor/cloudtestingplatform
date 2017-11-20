package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.ImageDao;
import com.mist.cloudtestingplatform.model.Image;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Prophet on 2017/11/18.
 */
@Repository
public class ImageDaoImpl extends DaoBase implements ImageDao {

    @Override
    public Image getImage(Integer id) {
        String hql = "from Image where id = :id";
        List<Image> rs = currentSession()
                .createQuery(hql).setInteger("id", id).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public void saveOrUpdateImage(Image image) {
        currentSession().saveOrUpdate(image);
    }
}
