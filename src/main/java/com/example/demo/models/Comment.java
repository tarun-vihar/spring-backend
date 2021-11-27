package com.example.demo.models;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Comment")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @ManyToOne
    @JoinColumn(name = "blog",referencedColumnName = "id",nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn (name = "commented_by",referencedColumnName = "username",nullable = false)
    private User user;

    @Column(name = "commented_at", nullable = false)
    private Date commentedAt;


    private String description;

    @Column
    private Date updatedAt;

    @Column
    private String sentimentType;


}
