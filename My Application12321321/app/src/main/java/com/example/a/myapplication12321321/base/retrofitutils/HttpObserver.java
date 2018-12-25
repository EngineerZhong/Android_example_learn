package com.example.a.myapplication12321321.base.retrofitutils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Time:2018/11/2 - 15:42
 * Author:Zhongwb
 * Description:
 */
public abstract class HttpObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        complete();
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        complete();
        onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);

    public abstract void complete();
}
