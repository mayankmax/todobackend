package com.example.todo.app.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDtos<T> {

    private String message;
    private String status;
    private T data;

    public UserResponseDtos() {
    }

    public UserResponseDtos(String message, String status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
