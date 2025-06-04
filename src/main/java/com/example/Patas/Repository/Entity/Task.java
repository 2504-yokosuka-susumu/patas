package com.example.Patas.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
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
    private int status;

    @Column(name = "limit_date")
    private Timestamp limitDate;

    @Column(insertable = false, updatable = false, name="created_date")
    private Timestamp createdDate;

    @Column(insertable = false, name="updated_date")
    private Timestamp updatedDate;
}
