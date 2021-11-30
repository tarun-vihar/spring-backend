package com.example.demo.repository;

import com.example.demo.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByEmail(String email);


    @Query(value = "SELECT * FROM Users u WHERE u.username = :username and " +
            "u.password = :password",
            nativeQuery = true)
    public User authenticateUserByName(String username, String password);
}
