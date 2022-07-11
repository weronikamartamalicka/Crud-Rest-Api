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
@CrossOrigin("*")
@RequestMapping("/v1/tasks")
@RestController
@RequiredArgsConstructor

public class TaskController {

    @GetMapping(value = "/tasks/getTasks")
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> taskList = dbService.getAllTasks();
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(taskList));
    }

    private final TaskMapper taskMapper;
    private final DbService dbService;
//
//
//    @GetMapping(value = "/tasks/getTasks")
//    public ResponseEntity<List<TaskDto>> getTasks() {
//        List<Task> taskList = dbService.getAllTasks();
//        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(taskList));
//    }
//
//
//    @GetMapping(value = "/tasks/getTask/{taskId}")
//    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
//        return ResponseEntity.ok(taskMapper.mapToTaskDto(dbService.getTaskById(taskId)));
//    }
//
//    @DeleteMapping(value = "/tasks/deleteTask/{taskId}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
//        dbService.deleteTaskById(taskId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(value = "/tasks/updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
//        Task task = taskMapper.mapToTask(taskDto);
//        Task savedTask = dbService.saveTask(task);
//        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
//    }
//
//    @PostMapping(value = "/tasks/createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
//        Task task = taskMapper.mapToTask(taskDto);
//        dbService.saveTask(task);
//        return ResponseEntity.ok().build();
//    }
}
