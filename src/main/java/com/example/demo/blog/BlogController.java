package com.example.demo.blog;


import com.example.demo.models.Blog;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    ResponseEntity responseEntity;
    @Autowired
    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }



    @PostMapping("/addBlog")
    public ResponseEntity insertBlog(@RequestBody  Blog blog){
        System.out.println(blog);
        responseEntity = new ResponseEntity(blogService.insertBlog(blog), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/get-all-blogs")
    public List<Blog> getBlogs(){

        return blogService.getAllBlogs();
    }

    @GetMapping("/get-blog")
    public ResponseEntity getBlogById(@RequestParam("username") String username){
         responseEntity = new ResponseEntity(blogService.getBlogsByUsername(username),HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity updateBlog(@PathVariable(value = "id") int blogId,
                                     @RequestBody Blog blogDetails){
        Blog blog = blogService.getBlog(blogId);
        if(blog != null){
            blogDetails.setId(blogId);
            return insertBlog(blogDetails);
        }else
            return  null;


    }






}
