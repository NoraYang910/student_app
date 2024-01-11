package com.example.students.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.students.entity.Student;
import com.example.students.mapper.StudentMapper;
import com.example.students.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
