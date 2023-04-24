package com.example.designnewsfeed.commands;

import com.example.designnewsfeed.controllers.AuthController;
import com.example.designnewsfeed.controllers.CommentController;
import com.example.designnewsfeed.dtos.CreateCommentDto;
import com.example.designnewsfeed.models.User;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;

@Component
public class CommentCommand implements Command{
    private AuthController authController;
    private CommentController commentController;

    public CommentCommand(AuthController authController, CommentController commentController) {
        this.authController = authController;
        this.commentController = commentController;
    }

    @Override
    public boolean canExecute(String input, MockHttpSession session) {
        String[] strs = input.split(" ");
        if(strs.length < 3) return false;
        if(!strs[0].equals(CommandKeywords.commentCommand)) return false;
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
        User dbUser = this.authController.getUserByEmail(sessionEmail);

        Long post_id = Long.valueOf(strs[1]);

        int secondSpaceIndex = input.indexOf(" ", input.indexOf(" ") + 1);
        String content = input.substring(secondSpaceIndex + 1);

        CreateCommentDto createCommentDto = new CreateCommentDto();
        createCommentDto.setContent(content);
        this.commentController.createComment(createCommentDto,dbUser,post_id);

    }
}
