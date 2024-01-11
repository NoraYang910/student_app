package com.example.students.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.students.common.Result;
import com.example.students.entity.Course;
import com.example.students.entity.PageQueryParam;
import com.example.students.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public Result selectAll(){
        List<Course> list = courseService.list();
        return Result.success(list.size(),list);
    }

    @PostMapping("/page")
    public Result pageList(@RequestBody PageQueryParam query){
        HashMap param = query.getParam();
        String name = (String) param.get("name");
        Page<Course> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getIsDelete,0);
        if(name!=null){
        lqw.like(Course::getName,name);
        }
        IPage result = courseService.page(page,lqw);
        return Result.success(result.getTotal(),result.getRecords());
    }

    // Add a new course
    @PostMapping("/add")
    public Result addCourse(@RequestBody Course course){
        return courseService.save(course)?Result.success():Result.fail();
    }

    // find a course by name
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String name){
        List<Course> list = courseService.lambdaQuery().eq(Course::getName, name).eq(Course::getIsDelete,0).list();
        System.out.println(list.size());
        return list.size()>0? Result.success(list.size(),list):Result.fail();
    }

    // update
    @PostMapping("/update")
    public Result updateCourse(@RequestBody Course course){
        return courseService.updateById(course)? Result.success():Result.fail();
    }

    // delete
    @GetMapping("/delete")
    public Result deleteCourse(@RequestParam Integer id){
        Course course = courseService.getById(id);
        course.setIsDelete(1);
        return courseService.updateById(course)? Result.success():Result.fail();
    }

    // query
    @PostMapping("/query")
    public Result queryCourse(@RequestBody Course course){
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper();
        lqw.like(Course::getName,course.getName()).eq(Course::getIsDelete,0);
        List<Course> list = courseService.list(lqw);
        return Result.success(list.size(),list);
    }
}
