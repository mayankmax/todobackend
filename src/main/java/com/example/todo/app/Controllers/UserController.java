package com.example.todo.app.Controllers;

import com.example.todo.app.DTOS.UserRequestDtos;
import com.example.todo.app.DTOS.UserResponseDtos;
import com.example.todo.app.Exceptions.UserException;
import com.example.todo.app.Models.Users;
import com.example.todo.app.Services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

 private UserServices userServices;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDtos<Users>> signup(@RequestBody @Valid UserRequestDtos userRequestDtos) throws IOException {


        System.out.println(userRequestDtos.getUserName() + userRequestDtos.getUserEmail() + userRequestDtos.getConfirmPassword() + userRequestDtos.getPassword());

        try {
            Users user = userServices.signup(userRequestDtos);

            if (user != null) {
                return ResponseEntity.ok(new UserResponseDtos<>("User created successfully", "success", user));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new UserResponseDtos<>("Internal server error", "error", null));
            }
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UserResponseDtos<>(e.getMessage(), "error", null));
        }
    }



    @GetMapping("/login")

    public ResponseEntity<UserResponseDtos<Users>> login(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword) throws IOException {
        try {
            Users user = userServices.login(userEmail, userPassword);

            if (user != null) {
                // User is found
                return ResponseEntity.ok(new UserResponseDtos<>("Logged in Successful", "success", user));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new UserResponseDtos<>("Internal server error", "error", null));
            }
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserResponseDtos<>(e.getMessage(),"error",null));
        }

    }


//    @DeleteMapping("/logout")
//    public ResponseEntity<ResponseDTO> logout(HttpServletRequest request) {
//        ResponseDTO responseDTO = new ResponseDTO();
//
//        // Clear the users session attribute
//        HttpSession httpSession = request.getSession(false);
//        if (httpSession != null) {
//            httpSession.removeAttribute("users");
//        }
//
//        responseDTO.setMessage("Session cleared successfully");
//        responseDTO.setStatus("Success");
//
//        return ResponseEntity.ok(responseDTO);
//    }

}
