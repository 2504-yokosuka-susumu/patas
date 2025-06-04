package com.example.Patas.service;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.repository.TaskRepository;
import com.example.Patas.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddService {
    @Autowired
    TaskRepository taskRepository;

    /*
     * レコード1件追加
     */
    public void saveTask(TaskForm raqTask) {
        Task saveTask = setTaskEntity(raqTask);
        taskRepository.save(saveTask);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setTaskEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setCreatedDate(reqTask.getCreatedDate());
        task.setUpdatedDate(new Date());
        return task;
    }
}
