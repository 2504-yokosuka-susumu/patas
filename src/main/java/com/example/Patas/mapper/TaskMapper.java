package com.example.Patas.mapper;

import com.example.Patas.repository.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectAll();
    Task selectById(int id);
    void insert(Task task);
    void updateById(Task task);
    void deleteById(int id);
}
