package com.example.designnewsfeed.repositories;

import com.example.designnewsfeed.models.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {
}
