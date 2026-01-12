package com.example.taskapp.domain;

public class Task {

    private Long id;
    private String title;
    private TaskStatus status;

    public Task() {
    }

    public Task(Long id, String title, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setStatus(TaskStatus status) { this.status = status; }
}
