package com.example.a.myapplication12321321.rxjava.observable_rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Time:2018/12/18 - 16:00
 * Author:Zhongwb
 * Description:
 */
public class RxUtils {

    private static final String TAG = "RxUtils";

    public static void createObservable(){

        //被观察者
        Observable<String> observable =  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if(!emitter.isDisposed()){
                    emitter.onNext("Hello World");
                    emitter.onNext("Hello Rxjava");
                    emitter.onNext(downloadJson());
                    emitter.onComplete();
                }
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG,"onSubscribe" + d.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"onNext" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete");
            }
        };

        observable.subscribe(observer);

    }


    public static String downloadJson(){
        return "{'name':'zwb','age':24}";
    }

}
