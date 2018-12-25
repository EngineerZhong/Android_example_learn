package com.example.a.myapplication12321321.base.retrofitutils;

import com.example.a.myapplication12321321.base.mvp.MyApplication;
import com.example.a.myapplication12321321.base.okhttputils.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Time:2018/11/2 - 9:52
 * Author:Zhongwb
 * Description:
 */
public class RetrofitManager {

    private static RetrofitManager instance = null;
    private Retrofit retrofit;

    private RetrofitManager(){
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.getServiceAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpManager.getInstance().getOkHttpClient())
                .build();
    }

    /**
     * 单例模式
     * @return
     */
    public static RetrofitManager getInstance(){
        if(instance == null) {
            synchronized (RetrofitManager.class){
                if(instance == null){
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    /**
     * 创建请求接口API
     */

    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }

}
