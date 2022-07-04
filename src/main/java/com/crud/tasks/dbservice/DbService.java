package com.crud.tasks.dbservice;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DbService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(final Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(final Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
