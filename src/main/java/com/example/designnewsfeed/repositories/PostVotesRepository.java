package com.example.designnewsfeed.repositories;

import com.example.designnewsfeed.models.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVotesRepository extends JpaRepository<PostVote,Long> {
}
