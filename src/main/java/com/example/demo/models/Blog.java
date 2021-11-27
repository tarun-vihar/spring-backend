package com.example.demo.models;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blog  {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;


    @Column(nullable = false)
    private String blogName;

    @Column(nullable = false)
    private String description;

    @ToString.Exclude
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by",referencedColumnName = "username",nullable = false)
//    @JsonBackReference
    private User user;

    @ManyToMany(mappedBy="blogList")
    private List<Tags> tagsList = new ArrayList<>();


    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;



    @ToString.Exclude
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();


}
