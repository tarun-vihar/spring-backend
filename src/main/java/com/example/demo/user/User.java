package com.example.demo.user;

import com.example.demo.models.Blog;
import com.example.demo.models.Comment;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "username")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;






//
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<Blog> blogs = new ArrayList<>();
//
//    @ToString.Exclude
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
////    @JsonManagedReference
//    private List<Comment> comments;



//    @ManyToMany()
//    @JoinTable(name="USER_RELATIONS",
//            joinColumns=@JoinColumn(name="USER_ID"),
//            inverseJoinColumns=@JoinColumn(name="FOLLOWED_BY"))
//    private List<User>followers = new ArrayList<>();




}
