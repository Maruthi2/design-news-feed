package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreatePostDto {
    private String content;
}
