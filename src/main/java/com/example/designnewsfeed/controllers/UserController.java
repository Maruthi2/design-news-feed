package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.CreateFollowDto;
import com.example.designnewsfeed.dtos.SignupUserRequestDto;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.models.UserFollow;
import com.example.designnewsfeed.models.UserFollowId;
import com.example.designnewsfeed.repositories.UserRepository;
import com.example.designnewsfeed.services.UserService;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void signupUser(SignupUserRequestDto requestDto){
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        this.userService.signupUser(user);
    }

    public void createFollowUser(CreateFollowDto createFollowDto, User user1) {
        UserFollow userFollow = new UserFollow();
        Optional<User> userOptional = this.userRepository.findById(createFollowDto.getUser2_id());
        User user2 = userOptional.get();
        UserFollowId userFollowId = new UserFollowId();
        userFollowId.setUser1(user1);
        userFollowId.setUser2(user2);
        userFollowId.setType(createFollowDto.getType());
        userFollow.setId(userFollowId);
        userFollow.setCreated_time(new Date());
        this.userService.createFollowUser(userFollow);
    }

}
