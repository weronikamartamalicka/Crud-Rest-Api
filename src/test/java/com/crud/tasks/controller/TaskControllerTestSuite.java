package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetTaskList() throws Exception {
        when(dbService.getAllTasks()).thenReturn(List.of(new Task(25l, "test", "test test")));
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(List.of(new TaskDto(25l, "test", "test test")));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(25)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test test")));

    }

    @Test
    void shouldGetTask() throws Exception {
        when(dbService.getTaskById(1l)).thenReturn((new Task(1l, "test", "test test")));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(new TaskDto(1l, "test", "test test"));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/{taskId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test test")));

    }

    @Test
    void shouldDeleteTask() throws Exception {
        when(taskMapper.mapToTask(new TaskDto(1l, "task", "task task")))
                .thenReturn(new Task(1l, "task", "task task"));
        when(dbService.saveTask(new Task(1l, "task", "task task"))).thenCallRealMethod();
        Gson gson = new Gson();
        String content = gson.toJson(new TaskDto(1l, "task", "task task"));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/{taskId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        when(taskMapper.mapToTask(new TaskDto(1l, "task", "task task")))
                .thenReturn(new Task(1l, "task", "task task"));
        when(dbService.saveTask(new Task(1l, "task", "task task"))).thenCallRealMethod();
        Gson gson = new Gson();
        String content = gson.toJson(new TaskDto(1l, "task", "task task"));

        mockMvc
                .perform(MockMvcRequestBuilders
                    .post("/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());

        when(taskMapper.mapToTask(new TaskDto(1l, "task updated", "task updated")))
                .thenReturn(new Task(1l, "task updated", "task updated"));
        when(dbService.saveTask(new Task(1l, "task updated", "task updated"))).thenCallRealMethod();
        String updatedContent = gson.toJson(new TaskDto(1l, "task updated", "task updated"));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(updatedContent))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void shouldCreateTask() throws Exception {
        when(taskMapper.mapToTask(new TaskDto(1l, "task", "task task")))
                .thenReturn(new Task(1l, "task", "task task"));
        when(dbService.saveTask(new Task(1l, "task", "task task"))).thenCallRealMethod();
        Gson gson = new Gson();
        String content = gson.toJson(new TaskDto(1l, "task", "task task"));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}