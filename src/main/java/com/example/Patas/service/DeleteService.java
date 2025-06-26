package com.example.Patas.service;

import com.example.Patas.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    @Autowired
    TaskMapper taskMapper;

    public void delete(Integer id) {
        taskMapper.deleteById(id);
    }
}
