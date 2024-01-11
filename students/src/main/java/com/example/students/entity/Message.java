package com.example.students.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private int id;
    @TableField("teacher_id")
    private int teacherId;
    private String subject;
    private String sender;
    private Date date;
    private String detail;
    @TableField("is_read")
    private int isRead;
    @TableField("is_delete")
    private int isDelete;
}
