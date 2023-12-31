package com.example.todo.app.Repository;

import com.example.todo.app.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long id);

    Users findByuserEmail(String email);

}
