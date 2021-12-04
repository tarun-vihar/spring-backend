package com.example.demo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_RELATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Followers {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="from_user")
    private User from;

    @ManyToOne
    @JoinColumn(name="to_user")
    private User to;

}
