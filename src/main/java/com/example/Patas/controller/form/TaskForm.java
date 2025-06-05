package com.example.Patas.controller.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class TaskForm {
    @NotNull(message = "不正なパラメータです")
//    @Pattern(regexp = "![0-9]*", message = "不正なパラメータです")
    private int id;

    @NotBlank(message = "タスクを入力してください")
    @Length(max= 140, message = "タスクは140文字以内で入力してください")
    private String content;

    private int status;

    @NotNull(message = "期限を設定してください")
    @Future(message = "無効な日付です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    private Date createdDate;

    private Date updatedDate;
}