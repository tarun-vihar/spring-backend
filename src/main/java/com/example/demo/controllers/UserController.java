package com.example.demo.controllers;

import com.example.demo.models.pojo.Response;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    AuthService authService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllUser(){
        return   new ResponseEntity(authService.getAllUsers(), OK);
    }

    @GetMapping("/get-unpublished-users")
    public ResponseEntity<Response>  getAllPublishedUsers(){
        return new ResponseEntity(authService.getUnpublisheUsers(), OK);
    }

    @GetMapping("/mostblogs/{date}")
    public ResponseEntity<Response> usersWithMaximumBlogs(final @PathVariable String date){
        return new ResponseEntity(authService.getUsersWithMaximumBlogsOnGivenDate(date),OK);

    }




}
