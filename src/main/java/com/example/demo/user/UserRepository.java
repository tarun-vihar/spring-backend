package com.example.demo.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "SELECT * FROM Users u WHERE u.username = :username and " +
            "u.password = :password",
            nativeQuery = true)
    public User authenticateUserByName(String username, String password);

    @Query(value = "SELECT COUNT(DISTINCT(EMAIL)) from Users u WHERE u.email = :email", nativeQuery = true)
    public int getEmailCount(String email);

    @Query(value = "SELECT * from Users ", nativeQuery = true)
    public List<User> getAllUsers();
}
