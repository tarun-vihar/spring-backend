package com.example.demo.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import java.sql.Date;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String text;

    @ManyToOne(fetch = LAZY)
    @NotNull
    @JoinColumn(name = "blogId", referencedColumnName = "blogId")
    private Blog blog;
    @NotNull
    private Date commentedAt;
    @ManyToOne(fetch = LAZY)
    @NotNull
    @JoinColumn(name = "commented_by", referencedColumnName = "username")
    private User user;
    @NotNull
    private String sentiment;
}

