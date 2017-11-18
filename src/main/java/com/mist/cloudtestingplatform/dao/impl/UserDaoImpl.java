package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.UserDao;
import com.mist.cloudtestingplatform.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prophet on 2016/12/20.
 */
@Repository
public class UserDaoImpl extends DaoBase implements UserDao {

    @Override
    public int countUser() {

        return 0;
    }

    @Override
    public User getUserByUsername(String username) {

        Session session = currentSession();

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
        Session session = currentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getUserByPassword(String username, String password) {

        Session session = currentSession();

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

        Session session = currentSession();
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

    @Override
    public List<User> getUserIn(Collection<Integer> idList) {
        if (idList == null || idList.size() == 0) {
            return null;
        }
        String hql = "from User where id in (:idList)";
        return currentSession()
                .createQuery(hql).setParameterList("idList", idList)
                .list();
    }
}
