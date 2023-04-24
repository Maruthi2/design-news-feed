package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCommentOnCommentDto {
    private String content;
    private Long comment_id;
}
