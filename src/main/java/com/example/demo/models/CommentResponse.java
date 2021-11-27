package com.example.demo.models;

import com.example.demo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {


    private long id;


    private Date commentedAt;


    private String description;

    private Date updatedAt;

    private String sentimentType;
}
