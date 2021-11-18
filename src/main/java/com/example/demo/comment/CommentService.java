package com.example.demo.comment;


import com.example.demo.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private  final  CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentByBlogId(int blogId){
        return commentRepository.findByBlog(blogId);
    }

    public int countTodayCommentsByUser(String username){
        return commentRepository.countTodayCommentsByUser(username);
    }

    public  int hasUserCommentOnBlog(String username,int blogId){
        return commentRepository.countBlogCommentByUser(username,blogId);
    }

}
