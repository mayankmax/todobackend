package com.example.todo.app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TaskProgress extends BaseModel {

    @ManyToOne
    @NotNull
    private Tasks task; // This represents the associated Task

    private int progress = 0; // Percentage progress

    public TaskProgress() {
    }
// Constructors, getters, setters, etc.
}

