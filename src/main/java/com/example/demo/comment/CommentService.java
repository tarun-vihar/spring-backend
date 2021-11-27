package com.example.demo.comment;

import com.example.demo.Blog.BlogRepository;
import com.example.demo.Blog.BlogService;
import com.example.demo.models.Blog;
import com.example.demo.models.Comment;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CommentService {

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    public Comment insertComment(Comment comment, String username, long blogId){

        User user = userService.getUserByName(username);
        Blog blog = blogService.getBlog(blogId);

        System.out.println(user);
        System.out.println(blog);
        if(user != null && blog != null){
            comment.setBlog(blog);
            comment.setUser(user);
            comment.setCommentedAt(new Date(System.currentTimeMillis()));
            System.out.println(comment);
            return commentRepository.save(comment);
        }

        return  null;

    }
}
