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
import java.util.List;

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

        @OneToMany(mappedBy="to")
        private List<Followers> followers = new ArrayList<>();

        @OneToMany(mappedBy="from")
        private List<Followers> following = new ArrayList<>();

}
