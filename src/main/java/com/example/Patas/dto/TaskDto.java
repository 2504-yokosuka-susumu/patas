package com.example.Patas.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {

    private int id;

    private String content;

    private Integer status;

    private Date limitDate;

    private Date createdDate;

    private Date updatedDate;
}
