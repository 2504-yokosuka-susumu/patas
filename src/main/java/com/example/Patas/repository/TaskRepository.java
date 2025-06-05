package com.example.Patas.repository;

import com.example.Patas.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
//    public List<Task> findAllByOderByLimitDate();
    public List<Task> findTop9ByLimitDateBetweenOrderByLimitDateAsc(Date start, Date end);
    public List<Task> findTop9ByLimitDateBetweenAndContentAndStatusOrderByLimitDateAsc(Date start, Date end, String content, int status);
    public List<Task> findTop9ByLimitDateBetweenAndContentOrderByLimitDateAsc(Date start, Date end, String content);
    public List<Task> findTop9ByLimitDateBetweenAndStatusOrderByLimitDateAsc(Date start, Date end, int status);
}
