package com.mist.cloudtestingplatform.service.impl;

import com.mist.cloudtestingplatform.dao.TaskDao;
import com.mist.cloudtestingplatform.model.Task;
import com.mist.cloudtestingplatform.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prophet on 2018/2/27.
 */
@Transactional
@Service
public class TaskServiceImpl implements TaskService{

    private TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void saveTask(Task task) {
        taskDao.saveTask(task);
    }

    @Override
    public Task getTaskByTaskName(String taskName) {
        return taskDao.getTaskByTaskName(taskName);
    }

    @Override
    public List<Task> listTaskByUserId(Integer userId, Integer offset, Integer length) {
        return taskDao.listTaskByUserId(userId, offset, length);
    }
}
