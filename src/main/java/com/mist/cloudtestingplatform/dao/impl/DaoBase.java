package com.mist.cloudtestingplatform.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Prophet on 2017/11/4.
 */
public abstract class DaoBase {

    protected SessionFactory sessionFactory;

    @Autowired
    public void UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
