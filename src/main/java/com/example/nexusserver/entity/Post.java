package com.example.nexusserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likes = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime createdAt;
}
