package com.example.demo.user;

import com.example.demo.models.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;


    @Test
    public void findAll(){
        List<User> users = userRepository.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void saveUser(){
        User user = User.builder().firstName("Tarun")
                .username("tarun-vihar")
                .email("tarun-email@gmail.com")
                .lastName("Vihar")
                .password("pass1234")
                .build();

        userRepository.save(user);
    }
}