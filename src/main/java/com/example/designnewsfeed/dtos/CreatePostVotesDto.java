package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostVotesDto {
    private int type;
    private Long post_id;
}
