package com.example.designnewsfeed.services;

import com.example.designnewsfeed.models.CommentVote;
import com.example.designnewsfeed.repositories.CommentVotesRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentVotesService {
    private CommentVotesRepository commentVotesRepository;

    public CommentVotesService(CommentVotesRepository commentVotesRepository) {
        this.commentVotesRepository = commentVotesRepository;
    }
    public void createCommentVotes(CommentVote commentVote){
        this.commentVotesRepository.save(commentVote);
    }
}
