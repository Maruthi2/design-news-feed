package com.example.designnewsfeed.controllers;

import com.example.designnewsfeed.dtos.CreatePostDto;
import com.example.designnewsfeed.models.Post;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.services.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void createPost(CreatePostDto createPostDto, User user){
        Post post = new Post();
        post.setContent(createPostDto.getContent());
        post.setUser(user);
        post.setCreated_time(new Date());
        this.postService.createPost(post);
    }

    public void getFeed(Long id) throws JsonProcessingException {
        this.postService.getFeed(id);
    }
}
