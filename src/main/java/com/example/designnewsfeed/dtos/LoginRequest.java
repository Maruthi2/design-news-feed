package com.example.designnewsfeed.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
