package com.example.students.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Teacher {
    private int id;
    private String name;
    private String password;
    private String academy;
    private String telephone;
    @TableField("image_url")
    private String imageUrl;

}
