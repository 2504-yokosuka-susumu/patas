package com.example.Patas.service;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.repository.TaskRepository;
import com.example.Patas.repository.entity.Task;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    //１．タスク情報取得処理
    public List<TaskForm> findAllTask(){
        List<Task> results = taskRepository.findAll();
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;

    }

    //２．タスク絞り込み情報取得処理
    public List<TaskForm> findSerchTask(String start, String end, TaskForm taskForm) throws ParseException, ParseException {
        // Date型に変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);

        List<Task> results = null;
        if(StringUtils.isBlank(taskForm.getContent()) && (taskForm.getStatus() == null)) {
            results = taskRepository.findTop9ByLimitDateBetweenOrderByLimitDateAsc(startDate, endDate);
        } else if(StringUtils.isBlank(taskForm.getContent())) {
            results = taskRepository.findTop9ByLimitDateBetweenAndStatusOrderByLimitDateAsc(startDate, endDate, taskForm.getStatus());
        } else if ((taskForm.getStatus() == null)){
            results = taskRepository.findTop9ByLimitDateBetweenAndContentOrderByLimitDateAsc(startDate, endDate, taskForm.getContent());
        } else {
            results = taskRepository.findTop9ByLimitDateBetweenAndContentAndStatusOrderByLimitDateAsc(startDate, endDate, taskForm.getContent(), taskForm.getStatus());
        }
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;

    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<TaskForm> setTaskForm(List<Task> results){
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setStatus(result.getStatus());
            task.setLimitDate((Timestamp) result.getLimitDate());
            tasks.add(task);
        }
        return tasks;
    }
}
