package com.example.demo.comment;


import com.example.demo.models.Blog;
import com.example.demo.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
@CrossOrigin(origins = "*")
public class CommentContoller {

    @Autowired
    private CommentService commentService;


    CommentContoller(CommentService commentService){
        this.commentService = commentService;
    }


    @PostMapping("/addComment/{user}/{blog}")
    public Comment insertBlog(@PathVariable(value = "user") String username,
                                     @PathVariable(value = "blog") long blogId,
                                     @RequestBody Comment comment) {



        System.out.println(username + " "+ blogId);
        System.out.println(comment);

        return  commentService.insertComment(comment, username, blogId);

    }






}
