package com.example.a.myapplication12321321.base.eventbusutils;

/**
 * Time:2018/10/29 - 14:52
 * Author:Zhongwb
 * Description:
 */
public class Event<T> {
    private int code;
    private String describe;
    private T data;


    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Event(int code, String describe, T data) {
        this.code = code;
        this.describe = describe;
        this.data = data;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

