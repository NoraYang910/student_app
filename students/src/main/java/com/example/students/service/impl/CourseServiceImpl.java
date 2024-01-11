package com.example.students.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.students.entity.Course;
import com.example.students.mapper.CourseMapper;
import com.example.students.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
}

