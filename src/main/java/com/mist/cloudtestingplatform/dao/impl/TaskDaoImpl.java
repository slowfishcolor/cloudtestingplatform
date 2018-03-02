package com.mist.cloudtestingplatform.dao.impl;

import com.mist.cloudtestingplatform.dao.TaskDao;
import com.mist.cloudtestingplatform.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Prophet on 2018/2/27.
 */
@Repository
public class TaskDaoImpl extends DaoBase implements TaskDao {

    @Override
    public void saveTask(Task task) {
        currentSession().saveOrUpdate(task);
    }

    @Override
    public Task getTaskByTaskName(String taskName) {
        String hql = "from Task where taskName = :tName";
        List<Task> rs = currentSession().createQuery(hql)
                .setString("tName", taskName).list();
        if (rs != null && rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    @Override
    public List<Task> listTaskByUserId(Integer userId, Integer offset, Integer length) {
        String hql = "from Task where userId = :uId order by timestamp desc";
        return currentSession().createQuery(hql)
                .setInteger("uId", userId)
                .setFirstResult(offset).setMaxResults(length).list();
    }

    @Override
    public Long getTaskCount() {
        String hql = "select count (*) from Task";
        return (Long) currentSession().createQuery(hql).uniqueResult();
    }

    @Override
    public Task getNewTask(Integer userId) {
        String hql = "from Task where userId = :uId order by timestamp desc ";
        List<Task> rs = currentSession()
                .createQuery(hql).setInteger("uId", userId)
                .setFirstResult(0).setMaxResults(1).list();
        return rs.get(0);
    }
}
