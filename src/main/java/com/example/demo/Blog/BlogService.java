package com.example.demo.Blog;

import com.example.demo.models.Blog;
import com.example.demo.models.BlogResponse;
import com.example.demo.models.BlogsResponseList;
import com.example.demo.models.Response;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserService userService;


    @Autowired
    ModelMapper modelMapper;

    Response response;

    BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
        response = new Response();
    }

    public Response getAllBlogs() {

                List<BlogResponse> blogResponses = new ArrayList<>();
        List<Blog> blogs = blogRepository.findAll();

                for(Blog blog : blogs){
                    blogResponses.add(modelMapper.map(blog, BlogResponse.class));
                }
//                collect(Collectors.toList());

        response.setData(blogResponses);
        response.setMessage("Sucessfully fetched");
        response.setStatus(true);
        return response;

    }

    public Response insertBlog(Blog blog, String username) {


        User user = findUser(username);
        List<Blog> data = new ArrayList<>();
        if(user == null){
            response.setStatus(false);
            response.setMessage("Not a registered user");

        }else if(blogRepository.countTodayBlogsByUser(user) >= 2){
           response.setMessage("User exceded Daily maximum limit of 2");
           response.setStatus(false);
        }else{
            blog.setUser(user);
            blog.setCreatedAt(new Date(System.currentTimeMillis()));
            data.add(blogRepository.save(blog));
            response.setMessage("Blog is published sucessfully");
            response.setStatus(true);

        }



       response.setData(data);
        return  response;
    }

    public Response getBlogsByUsername(String username){
        User user = findUser(username);
//        List<BlogResponse> blogResponses = new ArrayList<>();
//        List<Blog> blogs = blogRepository.findByUser(user);
//        blogResponses = Stream.of(blogs).
//                map(blog -> modelMapper.map(blog, BlogResponse.class)).
//                collect(Collectors.toList());

        ;

       response.setData(modelMapper.map(blogRepository.findByUser(user), BlogsResponseList.class));
       response.setMessage("Sucessfully fetched");
       response.setStatus(true);
        return response;
    }

    public Blog getBlog(long blogId){
        return  blogRepository.findById(blogId).orElse(null);
    }

    public User findUser(String username){
        User user = userService.getUserByName(username);
        return  user;
    }

    public Response deleteBlogId(long blogId) {
         blogRepository.deleteById(blogId);
        List<Blog> data = new ArrayList<>();
         response.setData(data);
         response.setMessage("Sucessfully Deleted");
         response.setStatus(true);

         return response;

    }
}
