package com.example.demo.models.pojo;

import com.example.demo.models.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {

        String[] tags;
        private Long blogId;
        private String username;
        private String blogName;
        private String description;

}
