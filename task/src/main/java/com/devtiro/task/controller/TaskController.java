package com.devtiro.task.controller;

import com.devtiro.task.domain.CreateTaskRequest;
import com.devtiro.task.domain.dto.CreateTaskRequestDto;
import com.devtiro.task.domain.dto.TaskDto;
import com.devtiro.task.domain.entity.Task;
import com.devtiro.task.mapper.TaskMapper;
import com.devtiro.task.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
        @Valid @RequestBody CreateTaskRequestDto dto
    ) {
        CreateTaskRequest request = taskMapper.fromDto(dto);
        Task task = taskService.createTask(request);
        TaskDto response = taskMapper.toDto(task);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        List<Task> tasks = taskService.listTasks();
        List<TaskDto> taskDtos = tasks.stream()
            .map(taskMapper::toDto)
            .toList();
        return ResponseEntity.ok(taskDtos);
    }
}
