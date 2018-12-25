package com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Time:2018/12/19 - 11:14
 * Author:Zhongwb
 * Description:
 */
public class PubuPresenter implements PubuContract.PubuPresenter {

    private static final String TAG = "PubuPresenter";
    private PubuContract.PubuView mView;
    private PubuContract.PubuModel mModel;

    public PubuPresenter(PubuContract.PubuView mView, PubuContract.PubuModel mModel) {
        this.mView = mView;
        this.mModel = mModel;
        mView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStateChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }
}
