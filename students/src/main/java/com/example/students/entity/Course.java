package com.example.students.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Course {
    private int id;
    private String name;
    private String lecturer;
    private String description;
    @TableField("image_url")
    private String imageUrl;
    @TableField("is_delete")
    private int isDelete;
}
