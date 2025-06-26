package com.example.Patas.service;


import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.mapper.TaskMapper;
import com.example.Patas.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EditService {

    @Autowired
    TaskMapper taskMapper;

    public TaskForm editTask(Integer id) {

        List<Task> results = new ArrayList<>();
        results.add(taskMapper.selectById(id));
        if(results.contains(null)) {
            return null;
        }else{

            List<TaskForm> tasks = setTaskForm(results);
            return tasks.get(0);
        }
    }

    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setLimitDate(result.getLimitDate());
            task.setStatus(result.getStatus());
            tasks.add(task);
        }
        return tasks;
    }

    public void saveTask(TaskForm reqTask) {
        Task saveTask = setReportEntity(reqTask);
        taskMapper.updateById(saveTask);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setReportEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setLimitDate(reqTask.getLimitDate());
        task.setStatus(reqTask.getStatus());
        task.setUpdatedDate(new Date());
        return task;
    }
}