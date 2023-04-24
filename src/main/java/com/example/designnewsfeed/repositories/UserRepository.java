package com.example.designnewsfeed.repositories;

import com.example.designnewsfeed.models.User;

import com.example.designnewsfeed.models.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
