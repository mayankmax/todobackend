package com.example.todo.app.Controllers;

import com.example.todo.app.DTOS.TaskDisplayRequestDtos;
import com.example.todo.app.DTOS.TaskRequestDtos;
import com.example.todo.app.DTOS.TaskResponseDtos;
import com.example.todo.app.Exceptions.TaskException;
import com.example.todo.app.Models.Tasks;
import com.example.todo.app.Services.TaskServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController{

    private TaskServices taskServices;

    @Autowired
    public TaskController(TaskServices taskServices){
        this.taskServices = taskServices;
    }


    @PostMapping("/addtask")
    public ResponseEntity<TaskResponseDtos<Tasks>> AddTask(@Valid @RequestBody TaskRequestDtos taskRequestDtos) {

        System.out.println(taskRequestDtos.getUserEmail());

        try {
            Tasks task = taskServices.Addtask(taskRequestDtos);

            return ResponseEntity.ok(new TaskResponseDtos<>("Task has been added Successfully","success",task));
        } catch (TaskException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TaskResponseDtos<>(e.getMessage(), "error", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDtos<>("Internal server error", "error", null));
        }
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/gettasks")
    public ResponseEntity<TaskResponseDtos<List<Tasks>>> getTasksForUserAndToday(
            @Valid @RequestBody TaskDisplayRequestDtos taskDisplayRequestDtos) {
        try {
            System.out.println(taskDisplayRequestDtos.getUserEmail());
            // Call the service to retrieve tasks for the user and today's date
            List<Tasks> tasks = taskServices.getTasksForUserAndToday(taskDisplayRequestDtos);

            return ResponseEntity.ok(new TaskResponseDtos<>("Tasks retrieved successfully", "success", tasks));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TaskResponseDtos<>("Internal server error", "error", null));
        }
    }







}



