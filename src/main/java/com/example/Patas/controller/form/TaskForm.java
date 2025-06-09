package com.example.Patas.controller.form;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TaskForm {
    @NotNull
    private int id;
    @NotBlank(message = "タスクを入力してください")
    @Length(max= 140, message = "タスクは140文字以内で入力してください")
    private String content;

    private Integer status;

    @NotNull(message = "期限を設定してください")
    @Future(message = "無効な日付です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    private Date createdDate;

    private Date updatedDate;
}
