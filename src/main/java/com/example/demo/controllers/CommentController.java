package com.example.demo.controllers;


import com.example.demo.models.dto.CommentDTO;
import com.example.demo.models.pojo.CommentRequest;
import com.example.demo.models.pojo.Response;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/comment")
@CrossOrigin(origins = "*")
public class CommentController {



    @Autowired
    CommentService commentService;

    @PostMapping("/post")
    public ResponseEntity<Response> insertComment(final  @RequestBody CommentRequest commentRequest) {

    return new ResponseEntity(commentService.insertComment(commentRequest),OK);

    }
}
