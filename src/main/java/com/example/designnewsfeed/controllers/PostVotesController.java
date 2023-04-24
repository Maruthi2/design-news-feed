package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.CreatePostVotesDto;
import com.example.designnewsfeed.models.Post;
import com.example.designnewsfeed.models.PostVote;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.repositories.PostRepository;
import com.example.designnewsfeed.services.PostVotesService;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostVotesController {
    private PostVotesService postVotesService;
    private PostRepository postRepository;

    public PostVotesController(PostVotesService postVotesService, PostRepository postRepository) {
        this.postVotesService = postVotesService;
        this.postRepository = postRepository;
    }

    public void createPostVotes(CreatePostVotesDto createPostVotesDto, User user){
        PostVote postVote = new PostVote();
        postVote.setType(createPostVotesDto.getType());
        postVote.setUser(user);
        postVote.setCreated_time(new Date());
        postVote.setCreated_time(new Date());
        Optional<Post> postOptional = this.postRepository.findById(createPostVotesDto.getPost_id());
        Post dbPost = postOptional.get();
        postVote.setPost(dbPost);
        this.postVotesService.createPostVotes(postVote);
    }
}
