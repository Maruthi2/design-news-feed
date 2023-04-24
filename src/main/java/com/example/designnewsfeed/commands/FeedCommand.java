package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.PostController;
import com.example.designnewsfeed.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class FeedCommand implements Command{
    private AuthController authController;
    private PostController postController;

    public FeedCommand(AuthController authController, PostController postController) {
        this.authController = authController;
        this.postController = postController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length != 1) return false;
        if(!strs[0].equals(CommandKeywords.feedCommand)) return false;
        String sessionEmail = (String) session.getAttribute("email");
        System.out.println("session Email = "+sessionEmail);
        if(sessionEmail == null ){
            System.out.println("Please login first");
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input, MockHttpSession session) throws JsonProcessingException {
        String[] strs = input.split(" ");

        String sessionEmail = (String) session.getAttribute("email");
        User user1 = this.authController.getUserByEmail(sessionEmail);
        this.postController.getFeed(user1.getId());

    }
}
