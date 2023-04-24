package com.example.designnewsfeed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PostVote extends BaseModel{
    private int type;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
