package com.devtiro.task.domain.dto;

import com.devtiro.task.domain.entity.TaskPriority;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

public record CreateTaskRequestDto(

    @NotBlank(message = "Title is required")
    @Length(max = 255, message = "Title must be at most 255 characters")
    String title,

    @Length(max = 1000, message = "Description must be at most 1000 characters")
    String description,

    @FutureOrPresent(message = "Due date must be today or in the future")
    LocalDate dueDate,

    @NotNull(message = "Task priority must be provided")
    TaskPriority priority

) {
}
