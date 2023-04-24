package com.example.designnewsfeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseModel {
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}
