package com.example.demo.student;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/getStudents")
    public  List<Student> getStudents(){
        return studentService.getStudents();
    }
}
