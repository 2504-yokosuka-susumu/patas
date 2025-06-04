package com.example.Patas.service;

import com.example.Patas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddService {
    @Autowired
    TaskRepository taskRepository;

}
