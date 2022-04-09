package com.library.utils;

import lombok.Data;

import java.io.Serializable;

//Json格式的而数据进行相应
@Data
public class JsonResult<E>implements Serializable {

    private Integer state;

    private String message;

    private E data;

    public JsonResult(){

    }

    public JsonResult(Integer state){
    this.state = state;
    }

    public JsonResult(Integer state, E data){
        this.state = state;
        this.data = data;
    }
}
