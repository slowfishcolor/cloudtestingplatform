package com.mist.cloudtestingplatform.dao;

import com.mist.cloudtestingplatform.model.Task;

import java.util.List;

/**
 * Created by Prophet on 2018/2/27.
 */
public interface TaskDao {

    public void saveTask(Task task);

    public Task getTaskByTaskName(String taskName);

    public List<Task> listTaskByUserId(Integer userId, Integer offset, Integer length);
}
