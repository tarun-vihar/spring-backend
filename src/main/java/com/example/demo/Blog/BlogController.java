package com.example.demo.Blog;

import com.example.demo.models.Blog;
import com.example.demo.models.Response;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    ResponseEntity responseEntity;
    @Autowired
    private final BlogService blogService;
    private final UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }


    @PostMapping("/addBlog/{user}")
    public Response insertBlog(@PathVariable(value = "user") String username,
                               @RequestBody Blog blog) {

        return  blogService.insertBlog(blog,username);
    }



    @GetMapping("/get-all-blogs")
    public Response getBlogs() {

        return blogService.getAllBlogs();
    }

    @GetMapping("/get-blogs/{username}")
    public ResponseEntity getBlogById(@PathVariable("username") String username) {
        responseEntity = new ResponseEntity(blogService.getBlogsByUsername(username), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/delete/{blogId}")
    public Response deleBlogById(@PathVariable("blogId") long blogId){
        return blogService.deleteBlogId(blogId);

    }


    public   Blog getBlog(long blogId){
        return  blogService.getBlog(blogId);
    }



}