package com.example.demo.models.dto;

import com.example.demo.models.entity.Comment;
import com.example.demo.models.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Long id;
    private String blogName;
    private String description;
    private String userName;
    private String firstName;
    private String[] tagsList;
    private List<CommentDTO> commentList;
    private Date createdAt;


}
