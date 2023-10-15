package com.example.todo.app.DTOS;

import com.example.todo.app.Models.Users;
import com.example.todo.app.Models.enums.TaskPriority;
import com.example.todo.app.Models.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskRequestDtos {

    private String taskName;
    private String taskDescription;
    private String taskDuration;
    private TaskStatus taskStatus = TaskStatus.InProgress;
    private TaskPriority taskPriority = TaskPriority.Low;
    private String userEmail;

}
