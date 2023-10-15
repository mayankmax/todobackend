package com.example.todo.app.Services;

import com.example.todo.app.DTOS.UserRequestDtos;
import com.example.todo.app.Exceptions.UserException;
import com.example.todo.app.Models.Users;
import com.example.todo.app.Repository.UserRepository;
import com.example.todo.app.Utils.ProfileConstraints;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service

public class UserServices {

    private UserRepository userRepository;

    @Autowired
    public  UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Users signup(UserRequestDtos userRequestDtos) throws UserException {

        String name = userRequestDtos.getUserName();
        String email = userRequestDtos.getUserEmail();
        String password = userRequestDtos.getPassword();
        String confirmPassword = userRequestDtos.getConfirmPassword();

        ProfileConstraints profileConstraints = new ProfileConstraints();

        try {
            if (userRepository.findByuserEmail(email) != null) {
                throw new UserException.UserAlreadyExist("Email already exists, please try to login or create account with new email");
            }

            String emailValidationResult = profileConstraints.isValidEmail(email);
            if (!"true".equals(emailValidationResult)) {
                throw new UserException.InvalidEmailException(emailValidationResult);
            }

            // Validate name
            String nameValidationResult = profileConstraints.isValidName(name);
            if (!"true".equals(nameValidationResult)) {
                throw new UserException.InvalidNameException(nameValidationResult);
            }

            // Validate phone
//        String phoneValidationResult = profileConstraints.isValidIndianPhoneNumber(phone);
//        if (!"true".equals(phoneValidationResult)) {
//            throw new UserException.InvalidPhoneException(phoneValidationResult);
//        }

            // Validate password
            String passwordValidationResult = profileConstraints.isValidPassword(password);
            if (!"true".equals(passwordValidationResult)) {
                throw new UserException.InvalidPasswordException(passwordValidationResult);
            }

            if (password.equals(confirmPassword) == false) {
                throw new UserException.InvalidPasswordException("Password Doesn't match");
            }

            Users user = new Users(name, email, password, confirmPassword);

            userRepository.save(user);

            return user;
        } catch (UserException.UserAlreadyExist e) {
            throw e;
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            // Log the exception
          //  logger.error("Exception occurred while signing up user: ", e);

            // Throw a generic exception
            throw new UserException("An unexpected error occurred while signing up. Please try again later.");
        }
    }




    public Users login(String email, String password)throws UserException {
        System.out.println(email);

        try {
            Users user = userRepository.findByuserEmail(email);

            if (user == null) {
                throw new UserException.InvalidEmailException("Email is not present in DB, please sign up to continue");
            }

            // Check if the provided password matches the stored password
            if (!user.getPassword().equals(password)) {
                throw new UserException.InvalidPasswordException("Incorrect password");
            }

            return user;
        } catch (UserException.InvalidEmailException e) {
            throw e;
        } catch (UserException.InvalidPasswordException e) {
            throw e;
        } catch (Exception e) {
            throw new UserException("An unexpected error occurred while logging in. Please try again later.");
        }
    }







}
