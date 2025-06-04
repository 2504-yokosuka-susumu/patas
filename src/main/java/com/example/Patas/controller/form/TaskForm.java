package com.example.Patas.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class TaskForm {
    @Pattern(regexp = "^\\d+$")
    @NotNull(message = "不正なパラメータです")
    private int id;
    @NotBlank(message = "タスクを入力してください")
    private String content;
    @NotBlank(message = "期限を設定してください")
    private int status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}
