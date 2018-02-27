package com.mist.cloudtestingplatform.service;

import com.mist.cloudtestingplatform.model.Task;

import java.util.List;

/**
 * Created by Prophet on 2018/2/27.
 */
public interface TaskService {

    public void saveTask(Task task);

    public Task getTaskByTaskName(String taskName);

    public List<Task> listTaskByUserId(Integer userId, Integer offset, Integer length);
}
