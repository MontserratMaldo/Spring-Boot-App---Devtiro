package com.devtiro.task.domain;

import java.time.LocalDate;

import com.devtiro.task.domain.entity.TaskPriority;

public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority
) {
}
