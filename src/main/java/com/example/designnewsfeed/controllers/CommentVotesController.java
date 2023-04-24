package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.CreateCommentVotesDto;
import com.example.designnewsfeed.models.Comment;
import com.example.designnewsfeed.models.CommentVote;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.repositories.CommentRepository;
import com.example.designnewsfeed.services.CommentVotesService;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class CommentVotesController {
    private CommentVotesService commentVotesService;
    private CommentRepository commentRepository;

    public CommentVotesController(CommentVotesService commentVotesService, CommentRepository commentRepository) {
        this.commentVotesService = commentVotesService;
        this.commentRepository = commentRepository;
    }

    public void createCommentVotes(CreateCommentVotesDto request, User user){
        CommentVote commentVote = new CommentVote();
        commentVote.setType(request.getType());
        Optional<Comment> commentOptional = commentRepository.findById(request.getComment_id());
        Comment comment = commentOptional.get();
        commentVote.setComment(comment);
        commentVote.setUser(user);
        commentVote.setCreated_time(new Date());
        this.commentVotesService.createCommentVotes(commentVote);
    }
}
