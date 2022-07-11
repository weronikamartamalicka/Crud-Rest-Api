package com.crud.tasks.controller;

import com.crud.tasks.service.DbService;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/tasks")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final TaskMapper taskMapper;
    private final DbService dbService;


    @GetMapping( "getTasks")
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> taskList = dbService.getAllTasks();
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(taskList));
    }


    @GetMapping("getTask/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskMapper.mapToTaskDto(dbService.getTaskById(taskId)));
    }

    @DeleteMapping("deleteTask/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        dbService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = dbService.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }

    @PostMapping(path ="createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
        return ResponseEntity.ok().build();
    }
}
