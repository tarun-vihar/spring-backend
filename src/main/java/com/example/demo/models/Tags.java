package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tags {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    @Column
    String name;

    @ManyToMany
    @JoinTable(name="BLOGS_DETAILS",
            joinColumns=@JoinColumn(name="BLOG_ID"),
            inverseJoinColumns=@JoinColumn(name="TAG_ID"))
    List<Blog> blogList = new ArrayList<>();

}
