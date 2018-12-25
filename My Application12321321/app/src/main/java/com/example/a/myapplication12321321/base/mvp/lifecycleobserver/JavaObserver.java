package com.example.a.myapplication12321321.base.mvp.lifecycleobserver;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Time:2018/12/11 - 9:42
 * Author:Zhongwb
 * Description:
 */


public class JavaObserver implements LifecycleObserver {

    private String TAG = "";

    public JavaObserver(String TAG) {
        this.TAG = TAG;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() { Log.i(TAG, "onCreate"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() { Log.i(TAG, "onStart"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() { Log.i(TAG, "onResume"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() { Log.i(TAG, "onPause"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() { Log.i(TAG, "onStop"); }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() { Log.i(TAG, "onDestroy"); }

}
