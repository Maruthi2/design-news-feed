package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.PostController;
import com.example.designnewsfeed.dtos.CreatePostDto;
import com.example.designnewsfeed.models.User;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

import java.nio.file.FileStore;

@Component
public class PostCommand implements Command{
    private AuthController authController;
    private PostController postController;

    public PostCommand(AuthController authController, PostController postController) {
        this.authController = authController;
        this.postController = postController;
    }

    @Override
    public boolean canExecute(String input,MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length < 2) return false;
        if(!strs[0].equals(CommandKeywords.postCommand)) return false;
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
        String sessionEmail = (String) session.getAttribute("email");
        String content = input.substring(input.indexOf(" ") + 1);

        CreatePostDto createPostDto = new CreatePostDto();
        createPostDto.setContent(content);
        User dbUser = this.authController.getUserByEmail(sessionEmail);
        this.postController.createPost(createPostDto,dbUser);
    }
}
