package com.example.students.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private int id;
    private String username;
    private String password;
    private int gender;
    private String academy;
    @TableField("birth_date")
    private Date birthDate;
    private String avatar;
}
