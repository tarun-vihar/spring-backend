package com.example.demo.models;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {


    private long id;

    private String blogName;

    private String description;



    private List<TagsResponse> tagsList = new ArrayList<>();

    private Date createdAt;

    private Date modifiedAt;

    private List<Comment> comments = new ArrayList<>();


}
