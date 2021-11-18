package com.example.demo.models;

import com.example.demo.user.User;
import jdk.jfr.Enabled;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @ManyToOne
    @JoinColumn(name = "blog_id",nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User commentedBy;

    @Column
    private Date commentedAt;

    @Column
    private String description;

    @Column
    private Date updatedAt;

    @Column
    private String sentimentType;

    Comment(){

    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", blog=" + blog +
                ", commentedBy=" + commentedBy +
                ", commentedAt=" + commentedAt +
                ", description='" + description + '\'' +
                ", updatedAt=" + updatedAt +
                ", sentimentType='" + sentimentType + '\'' +
                '}';
    }
}
