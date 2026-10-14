package com.social.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;
    private String authorAvatar;
    
    @Column(length = 1000)
    private String content;
    
    private String imageUrl;
    
    @Builder.Default
    private int likes = 0;
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
