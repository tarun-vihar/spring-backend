package com.example.demo.controllers;


import com.example.demo.models.dto.BlogDTO;
import com.example.demo.models.pojo.BlogRequest;
import com.example.demo.models.pojo.BlogResponse;
import com.example.demo.models.pojo.Response;
import com.example.demo.services.AuthService;
import com.example.demo.services.BlogService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/blog")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;
    private final AuthService userService;

    @PostMapping("/publish")
    public ResponseEntity<Response> insertBlog(final @NonNull @RequestBody BlogRequest blogRequest) {

        return  new ResponseEntity<>(blogService.insertBlog(blogRequest), OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogDTO>> getAllBlogs(){
        return  new ResponseEntity(blogService.getAllBlogs(), OK);
    }

    @DeleteMapping("delete/{blogId}")
    public ResponseEntity<Response> delete(final  @PathVariable long blogId){
        return new ResponseEntity(blogService.deleteBlog(blogId), OK);
    }

    @GetMapping("/user/{username}")
    public  ResponseEntity<Response> getAllUserBlogs(final  @PathVariable String username){
        return  new ResponseEntity(blogService.getAllBlogsByUserName(username), OK);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogDTO> getBlogDetails(final  @PathVariable long blogId){
        return new ResponseEntity(blogService.getBlogDetails(blogId), OK);
    }

    @GetMapping("/positive/{username}")
    public ResponseEntity<List<BlogDTO>>  getAllPostiveBlogs(final @PathVariable String username){
        return new ResponseEntity(blogService.findPositveBlogs(username), OK);
    }






}
