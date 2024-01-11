package com.example.students.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.students.common.Result;
import com.example.students.entity.Message;
import com.example.students.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    public Result selectAll(@RequestParam int teacherId){
        System.out.println("load message");
        List<Message> list = messageService.lambdaQuery().eq(Message::getTeacherId, teacherId)
                .eq(Message::getIsDelete,0)
                .orderByDesc(Message::getDate).list();
        return list.size()>0? Result.success(list.size(),list):Result.fail();
    }

    @GetMapping("/listDele")
    public Result selectDele(@RequestParam int teacherId){
        List<Message> list = messageService.lambdaQuery().eq(Message::getTeacherId, teacherId)
                .eq(Message::getIsDelete,1).orderByDesc(Message::getDate).list();
        return list.size()>0? Result.success(list.size(),list):Result.fail();
    }

    @PostMapping("/update")
    public Result updateMsg(@RequestBody Message message){
        if(message.getIsRead() == 1){
            // read
            message.setIsRead(0);
        }else if(message.getIsRead() == 0){
            // unread
            message.setIsRead(1);
        }
        return messageService.updateById(message)? Result.success():Result.fail();
    }

    @GetMapping("/unread")
    public Result unreadMsg(@RequestParam int teacherId){
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Message::getTeacherId, teacherId).eq(Message::getIsRead,0).eq(Message::getIsDelete,0).orderByDesc(Message::getDate);
        List<Message> list = messageService.list(lqw);
        return Result.success(list.size(),list);
    }

    @GetMapping("/read")
    public Result readMsg(@RequestParam int teacherId){
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Message::getTeacherId, teacherId).eq(Message::getIsRead,1).eq(Message::getIsDelete,0).orderByDesc(Message::getDate);
        List<Message> list = messageService.list(lqw);
        return Result.success(list.size(),list);
    }

    @PostMapping ("/delete")
    public Result deleteMsg(@RequestBody Message message){
        message.setIsDelete(1);
        return messageService.updateById(message)? Result.success(): Result.fail();
    }

    @PostMapping ("/restore")
    public Result restoreMsg(@RequestBody Message message){
        message.setIsDelete(0);
        return messageService.updateById(message)? Result.success(): Result.fail();
    }
}
