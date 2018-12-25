package com.example.a.myapplication12321321.base.okhttputils;

import android.util.Log;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;

/**
 * Time:2018/10/30 - 11:01
 * Author:Zhongwb
 * Description:
 */
public abstract class RequestCallback<T> {

    Type mType;

    public RequestCallback() {

        mType = getSuperClassTypeParameter(getClass());

    }


    static Type getSuperClassTypeParameter(Class<?> subClass){
        Type superClass = subClass.getGenericSuperclass();
        if(superClass instanceof Class){
            throw new RuntimeException("Missing type parameter.");
        }

        ParameterizedType parameterizedType = (ParameterizedType) superClass;


        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    public void onBefore(Request request){

    }

    public void onAfter(){

    }


    public abstract void onError(Request request, Exception e);


    public abstract void onResponse(T response);
}
