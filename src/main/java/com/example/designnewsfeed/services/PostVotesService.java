package com.example.designnewsfeed.services;

import com.example.designnewsfeed.models.PostVote;
import com.example.designnewsfeed.repositories.PostVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostVotesService {

    private PostVotesRepository postVotesRepository;
    @Autowired
    public PostVotesService(PostVotesRepository postVotesRepository) {
        this.postVotesRepository = postVotesRepository;
    }

    public void createPostVotes(PostVote postVote){
        postVotesRepository.save(postVote);
    }

}
