package com.example.demo.models.pojo;

import com.example.demo.models.entity.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private String text;
    private String username;
    private long blogId;
    private String sentiment;

}
