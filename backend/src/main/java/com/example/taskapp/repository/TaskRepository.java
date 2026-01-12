package com.example.taskapp.repository;

import com.example.taskapp.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    void save(Task task);

    Optional<Task> findById(Long id);

    void deleteById(Long id);
}

