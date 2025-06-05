package com.example.Patas.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    @Column
    private Integer status;

    @Column(name = "limit_date")
    private Date limitDate;

    @Column(insertable = false, updatable = false, name="created_date")
    private Date createdDate;

    @Column(insertable = false, updatable = false, name="updated_date")
    private Date updatedDate;
}
