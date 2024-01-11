package com.example.students.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.students.entity.Message;
import com.example.students.mapper.MessageMapper;
import com.example.students.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
}

