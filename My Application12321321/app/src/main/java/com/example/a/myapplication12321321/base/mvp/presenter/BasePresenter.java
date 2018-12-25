package com.example.a.myapplication12321321.base.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Time:2018/12/13 - 15:36
 * Author:Zhongwb
 * Description:
 */
public interface BasePresenter extends LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate();

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume();

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause();

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop();

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy();

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onStateChanged(LifecycleOwner owner, Lifecycle.Event event);

}
