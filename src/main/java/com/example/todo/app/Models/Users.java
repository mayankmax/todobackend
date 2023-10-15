package com.example.todo.app.Models;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users extends BaseModel{

    @NotNull(message = "This is required field, cannot be empty")
    private String userName;

    @NotNull(message = "This is required field, cannot be empty")
    @Email
    private String userEmail;

    @NotNull(message = "This is required field, cannot be empty")
    private String password;

    @NotNull(message = "This is required field, cannot be empty")
    private String confirmPassword;

    public Users() {
    }

    public Users(String userName, String userEmail, String password, String confirmPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }
}