package com.example.Patas.service;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.repository.Entity.Task;

import java.util.ArrayList;
import java.util.List;

public class EditService {

    List<Task> results = TaskRepository.findById(id).orElse(null);
    List<TaskForm> tasks = setTaskForm(results);

    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setLimitDate(result.getCreatedDate());
            tasks.add(task);
        }
        return tasks;
    }
}
