package com.example.demo.services;

import com.example.demo.models.dto.CommentDTO;
import com.example.demo.models.entity.Blog;
import com.example.demo.models.entity.Comment;
import com.example.demo.models.entity.LikeType;
import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.CommentRequest;
import com.example.demo.models.pojo.CommentResponse;
import com.example.demo.models.pojo.Response;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    AuthService authService;

    Response response;

    CommentService(){
        this.response = new Response();
    }

    public CommentDTO mapToCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setCommentedAt(comment.getCommentedAt());
        commentDTO.setBlogId(comment.getBlog().getBlogId());
        commentDTO.setSentiment(comment.getSentiment());
        commentDTO.setUserName(comment.getUser().getUsername());
        commentDTO.setFirstName(comment.getUser().getFirstName());

        return  commentDTO;

    }

    public CommentResponse getCommentResponse(List<Comment> commentList){
        CommentResponse commentResponse = new CommentResponse();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        commentList.forEach(comment -> {
            commentDTOS.add(mapToCommentDTO(comment));
        });
        commentResponse.setCommentResponse(commentDTOS);
        return  commentResponse;
    }


    public Response insertComment(CommentRequest commentRequest){


        User user = authService.getUserByUserName(commentRequest.getUsername());

        Blog blog = blogService.getBlogById(commentRequest.getBlogId());

        Response response = new Response();
        

        if(user != null && blog != null ){
            
            int userTodayComments = countTodayCommentsByUser(user);
            int countComentsByUserOnBlog = hasUserCommentOnBlog(user, blog);
            System.out.println(userTodayComments);
            System.out.println(countComentsByUserOnBlog);

            if(userTodayComments >=3 &&  countComentsByUserOnBlog >=1){
                response.setMessage("User Already Commented on this blog and exceded todays limit");

            } else if(userTodayComments >=3){
                response.setMessage("Maxmimum limits exceded ");
            } else if(countComentsByUserOnBlog>=1){
                response.setMessage("User Already Commented on this blog");
            }else {
                Comment comment = new Comment();
                comment.setBlog(blog);
                comment.setUser(user);
                comment.setCommentedAt(new Date(System.currentTimeMillis()));
                comment.setSentiment(commentRequest.getSentiment());
                comment.setText(commentRequest.getText());
                response.setData(mapToCommentDTO(commentRepository.save(comment)));
                response.setStatus(true);
            }
        }else{
            response.setMessage("User Or Blog doesn't exist");
        }


        return  response;


    }


//    public List<Comment> getCommentByBlogId(int blogId){
//        return commentRepository.findByBlogId(blogId);
//    }

    public int countTodayCommentsByUser(User user){
        return commentRepository.countTodayCommentsByUser(user);
    }

    public  int hasUserCommentOnBlog(User username,Blog blog){
        return commentRepository.countBlogCommentByUser(username,blog);
    }

}
