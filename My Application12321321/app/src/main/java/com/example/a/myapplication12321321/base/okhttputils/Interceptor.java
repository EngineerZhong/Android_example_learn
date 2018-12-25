package com.example.a.myapplication12321321.base.okhttputils;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time:2018/10/30 - 17:43
 * Author:Zhongwb
 * Description:
 */
public interface Interceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain {
        Request request();
        Response proceed(Request request) throws IOException;
        Connection connection();
    }
}
