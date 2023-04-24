package com.example.designnewsfeed.services;

import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.models.UserFollow;
import com.example.designnewsfeed.models.UserFollowId;
import com.example.designnewsfeed.repositories.UserFollowRepository;
import com.example.designnewsfeed.repositories.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    private UserRepository userRepository;
    private UserFollowRepository userFollowRepository;

    public UserService(UserRepository userRepository, UserFollowRepository userFollowRepository) {
        this.userRepository = userRepository;
        this.userFollowRepository = userFollowRepository;
    }

    public void signupUser(User user){
         this.userRepository.save(user);
    }

    public void createFollowUser(UserFollow userFollow) {
        if(userFollow.getId().getType().equals("unfollow")){
            userFollow.setStatus(2);
        } else {
            userFollow.setStatus(1);
        }
        this.userFollowRepository.save(userFollow);
        // followed by setup
        User user1 = userFollow.getId().getUser2();
        User user2 = userFollow.getId().getUser1();
        UserFollowId userFollowId = new UserFollowId();
        userFollowId.setUser1(user1);
        userFollowId.setUser2(user2);
        userFollowId.setType("FollowedBy");
        userFollow.setId(userFollowId);
        this.userFollowRepository.save(userFollow);
    }
}
