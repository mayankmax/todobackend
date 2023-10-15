package com.example.todo.app.DTOS;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequestDtos {

    private String userName;
    private String userEmail;
    private String password;
    private String confirmPassword;

}
