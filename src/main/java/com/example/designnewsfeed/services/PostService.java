package com.example.designnewsfeed.services;

import com.example.designnewsfeed.dtos.CreatePostDto;
import com.example.designnewsfeed.models.Post;
import com.example.designnewsfeed.models.User;
import com.example.designnewsfeed.repositories.PostRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        this.postRepository.save(post);
    }
    public void getFeed(Long user1Id) throws JsonProcessingException {
        List<?> feeds = this.postRepository.findFeedsByUser1Id(user1Id);
        List<Feed> newFeeds = new ArrayList<>();

//        System.out.println(feeds.size());
        for (Object obj : feeds) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(obj);
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            Feed feed = new Feed();
            feed.setTime_ago(jsonNode.get(0).asText());
            feed.setContent(jsonNode.get(1).asText());
            feed.setUpvotes(jsonNode.get(2).asInt());
            feed.setDownvotes(jsonNode.get(3).asInt());
            feed.setPost_score(jsonNode.get(4).asInt());
            feed.setComment_count(jsonNode.get(5).asInt());
            feed.setComment_score(jsonNode.get(6).asInt());
            newFeeds.add(feed);
        }

//        System.out.println(newFeeds.size());
//
        for (Feed feed : newFeeds) {
//            System.out.printf("%s %s %d %d %d %d %d %n", feed.getTime_ago(), feed.getContent(), feed.getUpvotes(),
//                    feed.getDownvotes(), feed.getPost_score(), feed.getComment_count(), feed.getComment_score());
            System.out.printf("%s %s %n", feed.getTime_ago(), feed.getContent());
        }
    }
}
