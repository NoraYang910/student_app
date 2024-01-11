package com.example.students.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.students.entity.Teacher;
import com.example.students.mapper.TeacherMapper;
import com.example.students.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
//    @Resource
//    private UserMapper userMapper;

//    @Override
//    public List<User> selectAll() {
//        return userMapper.selectAll();
//    }
}
