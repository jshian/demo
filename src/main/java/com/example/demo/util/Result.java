package com.example.demo.util;

import lombok.Data;

@Data
public class Result<T> {
    // error code
    private Integer code;
    // error message
    private String msg;
    // content
    private T data;

}
