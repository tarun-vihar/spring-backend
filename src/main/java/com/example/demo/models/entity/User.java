package com.example.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
        @Id
        @NotBlank(message = "Username is required")
        private String username;
        @NotBlank(message = "Password is required")
        private String password;
        @Email
        @NotEmpty(message = "Email is required")
        private String email;
        @NotEmpty
        private  String firstName;
        @NotEmpty
        private String lastName;
        private Instant created;

//        @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
//        @JoinTable(name = "USER_RELALTIONS",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "following_id"))
//        private List<User> following = new ArrayList<>();
//
//        @ManyToMany(mappedBy = "following")
//        private List<User> followers = new ArrayList<>();
//
        @OneToMany(mappedBy="username")
        private List<Relations> followers;

        @OneToMany(mappedBy="followerId")
        private List<Relations> following;

//        @OneToMany(mappedBy="username")
//        private List<RelationPrimaryKey> followers;
//
//        @OneToMany(mappedBy="followerId")
//        private List<RelationPrimaryKey> following;


}
