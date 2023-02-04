package com.example.demo.models.dto;


import com.example.demo.models.entity.LikeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long blogId;
    private Date commentedAt;
    private String text;
    private String userName;
    private String firstName;
    private String sentiment;
}
