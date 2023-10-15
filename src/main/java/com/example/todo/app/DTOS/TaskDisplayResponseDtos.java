package com.example.todo.app.DTOS;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskDisplayResponseDtos<T> {

    private String message;
    private String status;
    private T data;

    public TaskDisplayResponseDtos() {
    }

    public TaskDisplayResponseDtos(String message, String status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

}
