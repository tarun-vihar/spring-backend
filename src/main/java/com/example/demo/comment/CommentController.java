package com.example.demo.comment;

import com.example.demo.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/coment")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private final CommentService commentService;
    @Autowired
    private  final BlogService blogService;
    CommentController(CommentService commentService, BlogService blogService){
        this.commentService = commentService;
        this.blogService = blogService;
    }




}
