package com.example.a.myapplication12321321.base.retrofitutils;

import com.example.a.myapplication12321321.base.retrofitutils.bean.ResultBean;
import com.example.a.myapplication12321321.base.retrofitutils.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Time:2018/11/2 - 11:07
 * Author:Zhongwb
 * Description:
 */
public interface ServerApi {

    @FormUrlEncoded
    @POST("RetrofitGet")
    Observable<ResultBean<User>> loginGetUserInfo(@Query("method") String methodName,
                                                  @Field("username") String username,
                                                  @Field("password") String password);

    @POST("RetrofitGet")
    Observable<ResultBean<List<User>>> queryAll(@Query("method") String methodName);

    @POST("RetrofitGet")
    Observable<ResultBean<List<User>>> queryTestCallBack(@Query("method") String methodName, Callback<ResultBean<List<User>>> callback);
}
