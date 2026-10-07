package org.example.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {

    private Integer id;
    private String title;
    private String content;
    private Integer userId;

    private String createdTime;


}
