package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.PostVotesController;
import com.example.designnewsfeed.dtos.CreatePostVotesDto;
import com.example.designnewsfeed.models.User;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class DownvoteCommand implements Command{
    private AuthController authController;
    private PostVotesController postVotesController;

    public DownvoteCommand(AuthController authController, PostVotesController postVotesController) {
        this.authController = authController;
        this.postVotesController = postVotesController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length != 2) return false;
        if(!strs[0].equals(CommandKeywords.downvoteCommand)) return false;
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
        Long post_id = Long.valueOf(strs[1]);
        CreatePostVotesDto createPostVotesDto = new CreatePostVotesDto();
        createPostVotesDto.setType(2);
        createPostVotesDto.setPost_id(post_id);
        User dbUser = this.authController.getUserByEmail(sessionEmail);
        this.postVotesController.createPostVotes(createPostVotesDto,dbUser);

    }
}

