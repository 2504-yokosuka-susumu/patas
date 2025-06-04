package com.example.Patas.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TaskForm {
    private int id;
    @NotBlank(message = "タスクを入力してください")
    private String content;
    private int status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}
