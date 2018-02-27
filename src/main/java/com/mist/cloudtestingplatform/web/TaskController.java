package com.mist.cloudtestingplatform.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mist.cloudtestingplatform.annotation.Auth;
import com.mist.cloudtestingplatform.model.Task;
import com.mist.cloudtestingplatform.model.User;
import com.mist.cloudtestingplatform.protocol.model.Instruction;
import com.mist.cloudtestingplatform.service.MQServiceSmartGateway;
import com.mist.cloudtestingplatform.service.OperateResult;
import com.mist.cloudtestingplatform.service.OperateResultFactory;
import com.mist.cloudtestingplatform.service.TaskService;
import com.mist.cloudtestingplatform.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务页面相关
 * Created by Prophet on 2017/3/12.
 */
@Auth
@SessionAttributes("user")
@Controller
public class TaskController {

    private TaskService taskService;

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    private MQServiceSmartGateway mqServiceSmartGateway;

    @Autowired
    public void setMqServiceSmartGateway(MQServiceSmartGateway mqServiceSmartGateway) {
        this.mqServiceSmartGateway = mqServiceSmartGateway;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /***************************** pages *****************************/

    @RequestMapping(value = "/task-list", method = RequestMethod.GET)
    public String taskListPage(@ModelAttribute User user, Model model) {
        List<Task> tasks = taskService.listTaskByUserId(user.getId(), 0, 100);
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    /***************************** apis *****************************/
    @ResponseBody
    @RequestMapping(value = "/api/performTask", method = RequestMethod.POST)
    public OperateResult createTask(@ModelAttribute User user, String deviceId, String script, String instructionsJson) throws IOException {

        long messageId = IdUtils.generateMessageId();
        String taskName = IdUtils.generateTaskName();

        mqServiceSmartGateway.sendInstructions(deviceId, user.getId(), messageId, instructionsFormJson(instructionsJson));

        Task task = new Task();
        task.setDeviceId(deviceId);
        task.setUserId(user.getId());
        task.setMessageId(messageId + 1);
        task.setTaskName(taskName);
        task.setScript(script);
        task.setTimestamp(System.currentTimeMillis());

        taskService.saveTask(task);

        return OperateResultFactory.successResult();
    }


    private List<Instruction> instructionsFormJson(String instructionsJson) throws IOException {
        InstructionList instructionList = objectMapper.readValue(instructionsJson, InstructionList.class);
        return instructionList.getInstructions();
    }

    private static class InstructionList {
        private ArrayList<Instruction> instructions;

        public ArrayList<Instruction> getInstructions() {
            return instructions;
        }

        public void setInstructions(ArrayList<Instruction> instructions) {
            this.instructions = instructions;
        }
    }
}
