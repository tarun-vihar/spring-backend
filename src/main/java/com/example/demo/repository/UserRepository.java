package com.example.demo.repository;

import com.example.demo.models.entity.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByEmail(String email);


    @Query(value = "SELECT * FROM Users u WHERE u.username = :username and " +
            "u.password = :password",
            nativeQuery = true)
    public User authenticateUserByName(String username, String password);

//    Q4
    @Query(value = "SELECT * FROM Users u WHERE u.username " +
            "not in (select DISTINCT(published_by) from BLOG)",
            nativeQuery = true)
    public List<User> getUnpublishedUsers();



    @Query(value = "SELECT * FROM Users u WHERE u.username " +
            "in (select DISTINCT(published_by) from BLOG where created_date =:date)",
            nativeQuery = true)
    public List<User> getMostBlogsInADAy(Date date);

    @Query(value = "SELECT * FROM Users u WHERE u.username " +
            "not in (select DISTINCT(published_by) from BLOG)",
            nativeQuery = true)
    public List<User> usersWithNonNegativeCommentedBlogs();

    @Query(value = "SELECT * FROM Users u WHERE u.username " +
            "not in (select DISTINCT(published_by) from BLOG)",
            nativeQuery = true)
    public List<User> usersWithNegativeCommentedBlogs();





}
