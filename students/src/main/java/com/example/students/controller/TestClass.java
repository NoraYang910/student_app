package com.example.students.controller;

import com.example.students.entity.Student;
import com.example.students.entity.Teacher;
import com.example.students.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestClass {
    @GetMapping
    public String hello(){
        return "hello123";
    }

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public List<Teacher> list(){
        return teacherService.list();
    }

}
