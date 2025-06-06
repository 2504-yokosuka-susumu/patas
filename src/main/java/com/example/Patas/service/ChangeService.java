package com.example.Patas.service;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.repository.TaskRepository;
import com.example.Patas.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChangeService {
    @Autowired
    TaskRepository taskRepository;

    /*
     * レコード変更
     */
    public void saveTask(TaskForm reqTask) {
        Task saveTask = setTaskEntity(reqTask);
        taskRepository.updateStatusAndUpdatedDateById(saveTask.getId(), saveTask.getStatus(), new Date());
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setTaskEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setStatus(reqTask.getStatus());
        task.setLimitDate(reqTask.getLimitDate());
        task.setUpdatedDate(new Date());
        return task;
    }
}
