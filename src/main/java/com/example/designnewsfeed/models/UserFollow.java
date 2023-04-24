package com.example.designnewsfeed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollow {
    @EmbeddedId
    private UserFollowId id;

    @Column(columnDefinition = "int")
    @ColumnDefault("'1'")
    private int status;
    @CreatedDate
    private Date created_time;


}
