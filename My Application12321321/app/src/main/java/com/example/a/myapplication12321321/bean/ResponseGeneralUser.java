package com.example.a.myapplication12321321.bean;

/**
 * Time:2018/10/30 - 17:39
 * Author:Zhongwb
 * Description:
 */
public class ResponseGeneralUser<T> {

    int statueCode;
    String describe;
    T result;

    public ResponseGeneralUser(int statueCode, String describe, T result) {
        this.statueCode = statueCode;
        this.describe = describe;
        this.result = result;
    }

    public ResponseGeneralUser(int statueCode, String describe) {
        this.statueCode = statueCode;
        this.describe = describe;
    }

    public int getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(int statueCode) {
        this.statueCode = statueCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
