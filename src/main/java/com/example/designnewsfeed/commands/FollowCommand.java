package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.UserController;
import com.example.designnewsfeed.dtos.CreateFollowDto;
import com.example.designnewsfeed.models.User;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class FollowCommand implements Command{
    private AuthController authController;
    private UserController userController;

    public FollowCommand(AuthController authController, UserController userController) {
        this.authController = authController;
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length != 2) return false;
        if(!strs[0].equals(CommandKeywords.followCommand)) return false;
        String sessionEmail = (String) session.getAttribute("email");
        System.out.println("session Email = "+sessionEmail);
        if(sessionEmail == null ){
            System.out.println("Please login first");
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        String sessionEmail = (String) session.getAttribute("email");
        Long user2_id = Long.valueOf(strs[1]);
        String type = "Follow";
        CreateFollowDto createFollowDto = new CreateFollowDto();
        createFollowDto.setUser2_id(user2_id);
        createFollowDto.setType(type);
        User user1 = this.authController.getUserByEmail(sessionEmail);
        this.userController.createFollowUser(createFollowDto,user1);

    }
}
