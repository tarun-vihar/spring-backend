package com.example.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "users_relation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relations {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;



    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User username;

    @ManyToOne
    @JoinColumn(name="follower_id", nullable = false)
    private User followerId;

    //    @EmbeddedId
//    private RelationPrimaryKey relationKey;

}
