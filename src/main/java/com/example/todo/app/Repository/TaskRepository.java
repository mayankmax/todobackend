package com.example.todo.app.Repository;

import com.example.todo.app.Models.Tasks;
import com.example.todo.app.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUsersAndCreatedAt(Users users, LocalDate createdAt);
}
