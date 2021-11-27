package com.example.demo.Blog;

import com.example.demo.models.Blog;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public  void saveBlog(){

        User user = userRepository.findById("test-user").orElse(null);

        User user1 = User.builder().firstName("Tarun")
                        .username("tarun-vihar")
                                .email("tarun-email@gmail.com")
                                        .lastName("Vihar")
                                                .password("pass1234")
                                                        .build();



        System.out.println(user);
        Blog blog = Blog.builder()
                .blogName("Demons In Future ")
                .description("As  time passes, Desperation continues in the" +
                        "fans as Tennis  Stars Rafal Nadal and Federer reaches end of their" +
                        "fantabulos carier , jamming 20 Slams each. One regarded as King of the clay " +
                        "and other as magician  ")
                .user(user1)
                .build();

          blogRepository.save(blog);
        }

        @Test
        public void findAll(){
            List<Blog> blogs = blogRepository.findAll();
            for (Blog blog : blogs){
                System.out.println(blog);
            }
        }
}