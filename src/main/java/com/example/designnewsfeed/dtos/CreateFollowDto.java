package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateFollowDto {
    private Long user2_id;
    private String type; // database records holds Follow/FollowedBy
    // user can give input Follow or Unfollow only. Follow is status 1 and unfollow is status 2
    // in this requirement by default its follow only
}
