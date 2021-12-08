package com.example.demo.controllers;

import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.Response;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return   new ResponseEntity(authService.getAllUsers(), OK);
    }
// Q4
    @GetMapping("/get-unpublished-users")
    public ResponseEntity<List<UserDTO>>  getAllPublishedUsers(){
        return new ResponseEntity(authService.getUnpublisheUsers(), OK);
    }
//    Q2
    @GetMapping("/mostblogs/{date}")
    public ResponseEntity<List<UserDTO>> usersWithMaximumBlogs(final @PathVariable String date){
        return new ResponseEntity(authService.getUsersWithMaximumBlogsOnGivenDate(date),OK);
    }

//    Q5
    @GetMapping("/negative-commented-users")
    public  ResponseEntity<List<UserDTO>> finallAllNegativeCommnetedUsers(){
        return new ResponseEntity<>(authService.finallAllNegativeCommnetedUsers(),OK);
    }

//    Q6
    @GetMapping("/non-negative-commented-blogs")
    public ResponseEntity<List<UserDTO>> getPublishersOfNonNegativeBlogs(){
        return new ResponseEntity(authService.getPublishersOfNonNegativeBlogs(),OK);
    }


    @GetMapping("/get-common-following/{user1}/{user2}")
    public  ResponseEntity<List<UserDTO>> findCommonFollowing(@PathVariable String user1,
                                                              @PathVariable String user2){
        return new ResponseEntity(authService.findCommonFollowing(user1,user2),OK);
    }





}
