package com.example.taskapp.controller;

import com.example.taskapp.domain.Task;
import com.example.taskapp.domain.TaskStatus;
import com.example.taskapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.create(task.getTitle());
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        service.changeStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
