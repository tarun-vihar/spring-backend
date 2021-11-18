package com.example.demo.blog;

import com.example.demo.models.Blog;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return  blogRepository.findAll();
    }

    public Blog insertBlog(Blog blog) {
        System.out.println(blog);
       return blogRepository.save(blog);
    }

    public List<Blog> getBlogsByUsername(String username){
        return blogRepository.findByUsername(username);
    }

    public Blog getBlog(int blogId){
        return  blogRepository.getById(blogId);
    }
}
