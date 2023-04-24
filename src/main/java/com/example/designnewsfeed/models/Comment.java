package com.example.designnewsfeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Comment extends BaseModel{
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Comment comment;
}
