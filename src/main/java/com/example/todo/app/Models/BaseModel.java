package com.example.todo.app.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
}
