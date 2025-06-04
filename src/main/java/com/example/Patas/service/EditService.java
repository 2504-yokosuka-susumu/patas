package com.example.Patas.service;


import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.repository.entity.Task;
import com.example.Patas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditService {
    @Autowired
    TaskRepository taskRepository;

    public TaskForm editTask(Integer id) {

        List<Task> results = new ArrayList<>();
        results.add((Task) taskRepository.findById(id).orElse(null));
        List<TaskForm> reports = setTaskForm(results);

        return reports.get(0);
    }

    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setLimitDate(result.getCreatedDate());
        }
        return tasks;
    }

    public void saveTask(TaskForm reqTask) {
        Task saveTask = setReportEntity(reqTask);
        taskRepository.save(saveTask);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setReportEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setLimitDate((Timestamp) reqTask.getLimitDate());
        return task;
    }
}