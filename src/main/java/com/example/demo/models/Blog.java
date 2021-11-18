package com.example.demo.models;

import com.example.demo.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;


    @Column(nullable = false)
    private String blogName;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User username;

    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogName='" + blogName + '\'' +
                ", description='" + description + '\'' +
                ", username=" + username +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", comments=" + comments +
                '}';
    }

    public Blog(){}

    public Blog(long id, String blogName, String description, User createdBy, Date createdAt,
                Date modifiedAt, List<Comment> comments) {
        this.id = id;
        this.blogName = blogName;
        this.description = description;
        this.username = createdBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User createdBy) {
        this.username = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
