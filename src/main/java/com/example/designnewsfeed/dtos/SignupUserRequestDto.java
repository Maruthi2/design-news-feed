package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignupUserRequestDto {
    private String name;
    private String email;
    private String password;


}
