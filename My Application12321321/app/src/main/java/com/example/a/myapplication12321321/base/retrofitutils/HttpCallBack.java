package com.example.a.myapplication12321321.base.retrofitutils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Time:2018/11/2 - 17:14
 * Author:Zhongwb
 * Description:
 */
public class HttpCallBack<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }


}
