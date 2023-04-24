package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.CommentVotesController;
import com.example.designnewsfeed.dtos.CreateCommentVotesDto;
import com.example.designnewsfeed.models.User;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class CommentDownvoteCommand implements Command{
    private AuthController authController;
    private CommentVotesController commentVotesController;

    public CommentDownvoteCommand(AuthController authController, CommentVotesController commentVotesController) {
        this.authController = authController;
        this.commentVotesController = commentVotesController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length != 2) return false;
        if(!strs[0].equals(CommandKeywords.commentDownvoteCommand)) return false;
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
        Long comment_id = Long.valueOf(strs[1]);
        CreateCommentVotesDto createCommentVotesDto = new CreateCommentVotesDto();
        createCommentVotesDto.setType(2);
        createCommentVotesDto.setComment_id(comment_id);
        User dbUser = this.authController.getUserByEmail(sessionEmail);
        this.commentVotesController.createCommentVotes(createCommentVotesDto,dbUser);

    }
}
