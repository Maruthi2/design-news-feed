package com.example.designnewsfeed.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class UserFollowId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1; // use a more appropriate naming convention

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    @Column(name = "type")
    private String type;

    // constructors, getters, setters, and equals/hashCode methods
}
