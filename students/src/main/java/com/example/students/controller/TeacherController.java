package com.example.students.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.students.common.Result;
import com.example.students.entity.Course;
import com.example.students.entity.Teacher;
import com.example.students.mapper.TeacherMapper;
import com.example.students.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/list")
    public Result selectAll(){
        List<Teacher> list = teacherService.list();
        System.out.println( list.size());
        return Result.success(list.size(),list);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher){
        teacher.setPassword(encryptPassword(teacher.getPassword()));
        List<Teacher> list = teacherService.lambdaQuery().eq(Teacher::getName, teacher.getName())
                .eq(Teacher::getPassword, teacher.getPassword()).list();
        return list.size()>0?Result.success(list.get(0)):Result.fail();
    }

    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
