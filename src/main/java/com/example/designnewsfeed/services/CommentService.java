package com.example.designnewsfeed.services;

import com.example.designnewsfeed.models.Comment;
import com.example.designnewsfeed.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(Comment comment){
        this.commentRepository.save(comment);
    }
}
