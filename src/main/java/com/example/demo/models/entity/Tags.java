package com.example.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Tags {

//    @Id
//    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;


//    @ManyToMany
//    @JoinTable(name="BLOGS_DETAILS",
//            joinColumns=@JoinColumn(name="BLOG_ID"),
//            inverseJoinColumns=@JoinColumn(name="TAG_ID"))
    List<Blog> blogList = new ArrayList<>();

}
