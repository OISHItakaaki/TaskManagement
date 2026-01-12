package com.example.taskapp.repository;

import com.example.taskapp.domain.Task;
import com.example.taskapp.domain.TaskStatus;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CsvTaskRepository implements TaskRepository {

    private static final String FILE_PATH = "tasks.csv";

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                tasks.add(new Task(
                        Long.parseLong(values[0]),
                        values[1],
                        TaskStatus.valueOf(values[2])
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public void save(Task task) {
        List<Task> tasks = findAll();
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        tasks.add(task);
        writeAll(tasks);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return findAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        List<Task> tasks = findAll();
        tasks.removeIf(t -> t.getId().equals(id));
        writeAll(tasks);
    }

    private void writeAll(List<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                pw.println(
                        task.getId() + "," +
                        task.getTitle() + "," +
                        task.getStatus()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
