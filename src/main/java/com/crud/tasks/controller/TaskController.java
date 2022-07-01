package com.crud.tasks.controller;

import com.crud.tasks.dbservice.DbService;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/tasks")
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskMapper taskMapper;
    private final DbService dbService;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> taskList = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(taskList);
    }


    @GetMapping
    public TaskDto getTask(Long taskId) {
        Task task = dbService.getTaskById(taskId);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping
    public void deleteTask(Long taskId) {}

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {}

}
