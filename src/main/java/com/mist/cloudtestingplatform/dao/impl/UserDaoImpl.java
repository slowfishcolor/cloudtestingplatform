package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prophet on 2016/12/20.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int countUser() {

        return 0;
    }

    @Override
    public User getUserByUsername(String username) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from User where username = :username";

        Query query = session.createQuery(hql).setParameter("username", username);

        List result = query.list();

        if(result.size() != 0) {
            return (User)result.get(0);
        }

        return null;
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getUserByPassword(String username, String password) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from User where username = :username and password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("username", username).setParameter("password", password);

        List result = query.list();

        if(result.size() != 0) {
            return (User)result.get(0);
        }

        return null;
    }

    @Override
    public void saveUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        // session.beginTransaction();
        session.save(user);
        System.out.println(user.getId());
        // session.getTransaction().commit();


    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
