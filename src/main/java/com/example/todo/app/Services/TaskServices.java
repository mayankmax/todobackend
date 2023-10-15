package com.example.todo.app.Services;

import com.example.todo.app.DTOS.TaskDisplayRequestDtos;
import com.example.todo.app.DTOS.TaskRequestDtos;
import com.example.todo.app.Exceptions.TaskException;
import com.example.todo.app.Exceptions.UserException;
import com.example.todo.app.Models.Tasks;
import com.example.todo.app.Models.Users;
import com.example.todo.app.Models.enums.TaskPriority;
import com.example.todo.app.Repository.TaskRepository;
import com.example.todo.app.Repository.UserRepository;
import com.example.todo.app.Utils.TaskConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class TaskServices {

    private TaskRepository taskRespository;
    private UserRepository userRepository;

    @Autowired
    public TaskServices(TaskRepository taskRespository,UserRepository userRepository){
        this.taskRespository = taskRespository;
        this.userRepository = userRepository;
    }

    public Tasks Addtask(TaskRequestDtos taskRequestDtos) throws TaskException {

        String taskName = taskRequestDtos.getTaskName();
        String taskDescription = taskRequestDtos.getTaskDescription();
        String taskDuration = taskRequestDtos.getTaskDuration();
        TaskPriority taskPriority = taskRequestDtos.getTaskPriority(); // Assuming it's a TaskPriority object, not a String
        //Users user = userRepository.findByuserEmail("kashyap@gmail.com");
        String email = taskRequestDtos.getUserEmail();
        Users user = userRepository.findByuserEmail(email);



        System.out.println(user);

        TaskConstraints taskConstraints = new TaskConstraints();

        try {

            if(user == null)
                throw new RuntimeException("User is not Verified, please login");



            String nameMessage = taskConstraints.isValidTaskName(taskName);
            if (nameMessage != null)
                throw new TaskException.NotValidName(nameMessage);

            String descriptionMessage = taskConstraints.isValidTaskDescription(taskDescription);
            if (descriptionMessage != null)
                throw new TaskException.NotValidDescription(descriptionMessage);

            String durationMessage = taskConstraints.isValidTaskDuration(taskDuration);
            if (durationMessage != null)
                throw new TaskException.NotValidDuration(durationMessage);

            String priorityMessage = taskConstraints.isValidTaskPriority(taskPriority);
            if (priorityMessage != null)
                throw new TaskException.NotValidPriority(priorityMessage);

            // If all validations pass, create a new Tasks object
            Tasks task = new Tasks();
            task.setTaskName(taskName);
            task.setTaskDescription(taskDescription);
            task.setTaskDuration(taskDuration);
            task.setTaskPriority(taskPriority);
            task.setUsers(user);
            Date date = new Date();
            LocalDate localDate = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            task.setCreatedAt(localDate);

            // Set other properties as needed
            taskRespository.save(task);

            return task; // Return the newly created task

        } catch (TaskException e) {
            // Here, TaskException is a custom exception class
            // You can provide a meaningful error message to the client
            throw new TaskException("Error while adding task: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected exceptions and handle them appropriately
            // For example, log the error, provide a generic message to the client, etc.
            throw new TaskException("An unexpected error occurred: " + e.getMessage());
        }
    }


    public List<Tasks> getTasksForUserAndToday(TaskDisplayRequestDtos taskDisplayRequestDtos) {
        try {

            Date date = new Date();
            LocalDate localDate = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            Users user = userRepository.findByuserEmail(taskDisplayRequestDtos.getUserEmail());
            return taskRespository.findByUsersAndCreatedAt(user, localDate);
        } catch (Exception e) {
            e.printStackTrace(); // Example: Printing the stack trace
            return null; // Or throw a custom exception, return an empty list, etc.
        }
    }
}
