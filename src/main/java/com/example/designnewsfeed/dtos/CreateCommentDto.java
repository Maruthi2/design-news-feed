package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCommentDto {
    private String content;
    private Long post_id;
}
