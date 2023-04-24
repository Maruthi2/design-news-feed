package com.example.designnewsfeed.repositories;

import com.example.designnewsfeed.models.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVotesRepository extends JpaRepository<CommentVote,Long> {
}
