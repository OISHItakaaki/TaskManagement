package com.example.taskapp.service;

import com.example.taskapp.domain.Task;
import com.example.taskapp.domain.TaskStatus;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task create(String title) {
        Task task = new Task(
                idGenerator.getAndIncrement(),
                title,
                TaskStatus.TODO
        );
        repository.save(task);
        return task;
    }

    public void changeStatus(Long id, TaskStatus status) {
        Task task = repository.findById(id).orElseThrow();
        task.setStatus(status);
        repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
