package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.LoginRequest;
import com.example.designnewsfeed.dtos.SignupRequest;
import com.example.designnewsfeed.exceptions.EmailAlreadyExistsException;
import com.example.designnewsfeed.exceptions.InvalidCredentialsException;
import com.example.designnewsfeed.exceptions.UserNotFoundException;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
public class AuthController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            System.out.println("Sorry.Email Address already in use!. Please use another email.");
            return ;
//            throw new EmailAlreadyExistsException("Email Address already in use!");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setCreated_time(new Date());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        System.out.println("User registered successfully");
    }


    public boolean authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null) {
            System.out.println("User Not found with given Email:"+loginRequest.getEmail());
            return false;
//            throw new UserNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Invalid Credentials");
            return false;
//            throw new InvalidCredentialsException();
        }
        System.out.println("User logged in successfully!");
        return true;
    }
    public User getUserByEmail(String  email){
        return userRepository.findByEmail(email);
    }
}
