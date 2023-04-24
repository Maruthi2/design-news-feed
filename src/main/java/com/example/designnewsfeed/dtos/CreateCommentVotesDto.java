package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCommentVotesDto {
    private int type;
    private Long comment_id;
}
