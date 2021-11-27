package com.example.demo.models;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
public class Response {
    private boolean status;
    private String message;
    private Object data;

}
