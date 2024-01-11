package com.example.students.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private long total;
    private Object data;
    public static Result result(int code,String msg,long total,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public static Result fail(){
        return result(400,"failure",0,null);
    }

    public static Result success(){
        return result(200,"success",0,null);
    }
//    public static Result success(String msg){
//        return result(200,msg,0,null);
//    }
    public static Result success(Object data){
        return result(200,"success",0,data);
    }

    public static Result success(long total,Object data){
        return result(200,"success",total,data);
    }
}
