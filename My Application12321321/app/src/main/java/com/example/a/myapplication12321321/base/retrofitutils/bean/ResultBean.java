package com.example.a.myapplication12321321.base.retrofitutils.bean;

import java.io.Serializable;

/**
 * Time:2018/11/2 - 11:11
 * Author:Zhongwb
 * Description:服务器返回参数Json格式化
 */
public class ResultBean<T> implements Serializable{

/**
 * statueCode
 * 400 前期验证和初始化接口服务阶段，非法请求。
 * 500 服务异常，服务执行过程中。
 * 200 服务正常返回结果数据。
 */
    private int statueCode;

//  信息描述
    private String describe;
//  输出结果类型
    private T result;

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
