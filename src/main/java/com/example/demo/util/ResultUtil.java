package com.example.demo.util;


public class ResultUtil{
    public static Result success(Object o) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(o);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
