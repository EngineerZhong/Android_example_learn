package com.example.a.myapplication12321321.base.retrofitutils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/**
 * Time:2018/11/2 - 15:32
 * Author:Zhongwb
 * Description:
 */
public abstract class HttpSubscriber<T> implements Subscriber<T> {


    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {
        complete();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        complete();
        onError(t.getMessage());
    }

    @Override
    public void onComplete() {

    }



    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

    public abstract void complete();
}
