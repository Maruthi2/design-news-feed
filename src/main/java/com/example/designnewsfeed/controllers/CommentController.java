package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.CreateCommentDto;
import com.example.designnewsfeed.dtos.CreateCommentOnCommentDto;
import com.example.designnewsfeed.models.Comment;
import com.example.designnewsfeed.models.Post;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.repositories.CommentRepository;
import com.example.designnewsfeed.repositories.PostRepository;
import com.example.designnewsfeed.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentController(CommentService commentService, PostRepository postRepository, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void createComment(CreateCommentDto createCommentDto, User user, Long post_id){
        Comment comment = new Comment();
        comment.setContent(createCommentDto.getContent());
        comment.setUser(user);
        comment.setCreated_time(new Date());
        Optional<Post> postOptional = this.postRepository.findById(post_id);
        Post dbPost = postOptional.get();
        comment.setPost(dbPost);
        this.commentService.createComment(comment);
    }

    public void createCommentOnComment(CreateCommentOnCommentDto request, User user){
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        Optional<Comment> currentComment  = this.commentRepository.findById(request.getComment_id());
        Comment comment1 = currentComment.get();
        Optional<Post> postOptional = this.postRepository.findById(comment1.getPost().getId());
        Post dbPost = postOptional.get();
        comment.setPost(dbPost);
        comment.setCreated_time(new Date());
        comment.setComment(comment1);
        this.commentService.createComment(comment);
    }

}
