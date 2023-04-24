package com.example.designnewsfeed.services;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Feed {
    private String time_ago;
    private String content;
    private int upvotes;
    private int downvotes;
    private int post_score;
    private int comment_count;
    private int comment_score;
}
