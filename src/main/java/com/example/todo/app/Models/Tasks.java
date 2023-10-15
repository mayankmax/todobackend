package com.example.todo.app.Models;

import com.example.todo.app.Models.enums.TaskPriority;
import com.example.todo.app.Models.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Tasks extends BaseModel{

    @NotNull
    private String taskName;
    private LocalDate createdAt;
    @NotNull
    private String taskDescription;
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus taskStatus = TaskStatus.Not_Started;
    @Enumerated(EnumType.ORDINAL)
    private TaskPriority taskPriority = TaskPriority.Low;
    @ManyToOne
    @NotNull
    private Users users;
    @NotNull
    private String taskDuration;
    public Tasks() {
    }
}
