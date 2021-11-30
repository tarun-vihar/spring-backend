package com.example.demo.services;

import com.example.demo.models.dto.BlogDTO;
import com.example.demo.models.dto.TagDTO;
import com.example.demo.models.entity.Blog;
import com.example.demo.models.entity.Tags;
import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.BlogRequest;
import com.example.demo.models.pojo.BlogResponse;
import com.example.demo.models.pojo.CommentResponse;
import com.example.demo.models.pojo.Response;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;



    @Autowired
    AuthService authService;

    @Autowired
    CommentService commentService;

    Response response;


    BlogService(){

    }

    @Autowired
    ModelMapper modelMapper;


//     return blogRepository.findAll().stream().map(blog -> {
//        return modelMapper.map(blog,BlogResponse.class);
//    }).collect(Collectors.toList());

    public  List<BlogDTO> getAllBlogs(){
        List<Blog> blogs = blogRepository.findAll();

        return  getBlogResponse(blogs).getBlogResponse();

    }

    private BlogResponse getBlogResponse(List<Blog> blogs) {

        BlogResponse blogResponse= new BlogResponse();
        List<BlogDTO>  blogDTOS = new ArrayList<>();

        blogs.forEach(blog -> {
             blogDTOS.add(mapToBlogDTO(blog));}
        );

        blogResponse.setBlogResponse(blogDTOS);
        return  blogResponse;
    }


    public Response insertBlog(BlogRequest blogRequest) {

        User user = authService.getUserByUserName(blogRequest.getUsername());
        response = new Response();

        if(user == null){
            response.setStatus(false);
            response.setMessage("Not a registered user");

        }else if(blogRepository.countTodayBlogsByUser(user) >= 2){
            response.setMessage("User exceded Daily maximum limit of 2");
            response.setStatus(false);
        }else{

            Blog blog = new Blog();
            blog.setUser(user);
            blog.setCreatedAt(new Date(System.currentTimeMillis()));
//
            blog.setTags(blogRequest.getTags());
            blog.setBlogName(blogRequest.getBlogName());
            blog.setDescription(blogRequest.getDescription());
//
            response.setMessage("Blog is published sucessfully");
            response.setStatus(true);
            Blog result = blogRepository.save(blog);
            response.setData(mapToBlogDTO(result));

        }

        return  response;
    }

    public BlogDTO mapToBlogDTO(Blog blog){

        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getBlogId());
        CommentResponse commentResponse = commentService.getCommentResponse(blog.getComments());
        blogDTO.setCommentList(commentResponse.getCommentResponse());
        blogDTO.setDescription(blog.getDescription());
        blogDTO.setTagsList(blog.getTags());
        blogDTO.setUserName(blog.getUser().getUsername());
        blogDTO.setFirstName(blog.getUser().getFirstName());
        blogDTO.setBlogName(blog.getBlogName());
        return blogDTO;
    }


    public Response deleteBlog(long blogId) {

        Blog blog = blogRepository.findById(blogId).orElse(null);

        if(blog != null){
            blogRepository.delete(blog);
            response.setMessage("Sucessfully deleted");
            response.setStatus(true);
        }
        else{
            response.setMessage("No Blog doesn't exist ");
            response.setStatus(false);
        }

        return  response;

    }

    public Response getAllBlogsByUserName(String username) {
        response = new Response();
        User user= authService.getUserByUserName(username);
        if(user == null){
            response.setMessage("User Not Found Successfully");
        }
        else{
            List<Blog> blogs = blogRepository.findByUser(user);
            response.setData(getBlogResponse(blogs));
            response.setStatus(true);
        }

        return  response;

    }

    public  List<Tags> convertToTagList(List<String> tagNames){
        List<Tags> tagsList = new ArrayList<>();
        tagNames.forEach(tagName -> {
            Tags tag = new Tags();
            tag.setName(tagName);
            tagsList.add(tag);
        });
        return tagsList;
    }

    public  Blog   getBlogById(long blogId){

        Blog blog = blogRepository.findById(blogId).orElse(null);

        return blog;
    }

    public BlogDTO getBlogResponse(long blogId){
        Blog blog = getBlogById(blogId);
        if(blog != null)
          return  mapToBlogDTO(blog);
        return  null;
    }

}
