package com.example.demo.models.entity;



import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long blogId;
    @NotBlank(message = "Blog Name cannot be empty or Null")
    private String blogName;

    @Nullable
    @Lob
    private String description;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "published_by", referencedColumnName = "username")
    private User user;
    private Date createdAt;

//    @ManyToMany(mappedBy = "blogList",fetch = LAZY)
//    private List<Tags> tagsList = new ArrayList<>();
//    private String[] tags;

    @Column(columnDefinition = "text[]")
    @Type(type = "com.example.demo.config.CustomStringArrayType")
    private String[] tags;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
