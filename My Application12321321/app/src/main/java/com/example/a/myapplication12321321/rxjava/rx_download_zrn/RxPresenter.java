package com.example.a.myapplication12321321.rxjava.rx_download_zrn;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail.MenuEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2018/12/18 - 17:34
 * Author:Zhongwb
 * Description:
 */
public class RxPresenter implements RxContract.RxPresenter {

    private static final String TAG = "RxPresenter";

    private RxContract.RxModel rxModel;
    private RxContract.RxView rxView;

    public RxPresenter(RxContract.RxModel rxModel, RxContract.RxView rxView) {
        this.rxModel = rxModel;
        this.rxView = rxView;
        this.rxView.setPresenter(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void downloadFromBytes(String url) {
        rxModel.downloadFileFromBytes(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Log.d(TAG, "onNext");
                        rxView.setImage(bytes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void initMenuEntity() {
        List<MenuEntity> homeEntrances = new ArrayList<>();
        homeEntrances.add(new MenuEntity("美食", R.mipmap.ic_category_0));
        homeEntrances.add(new MenuEntity("电影", R.mipmap.ic_category_1));
        homeEntrances.add(new MenuEntity("酒店住宿", R.mipmap.ic_category_2));
        homeEntrances.add(new MenuEntity("生活服务", R.mipmap.ic_category_3));
        homeEntrances.add(new MenuEntity("KTV", R.mipmap.ic_category_4));
        homeEntrances.add(new MenuEntity("旅游", R.mipmap.ic_category_5));
        homeEntrances.add(new MenuEntity("学习培训", R.mipmap.ic_category_6));
        homeEntrances.add(new MenuEntity("汽车服务", R.mipmap.ic_category_7));
        homeEntrances.add(new MenuEntity("摄影写真", R.mipmap.ic_category_8));
        homeEntrances.add(new MenuEntity("休闲娱乐", R.mipmap.ic_category_10));
        homeEntrances.add(new MenuEntity("丽人", R.mipmap.ic_category_11));
        homeEntrances.add(new MenuEntity("运动健身", R.mipmap.ic_category_12));
        homeEntrances.add(new MenuEntity("大保健", R.mipmap.ic_category_13));
        homeEntrances.add(new MenuEntity("团购", R.mipmap.ic_category_14));
        homeEntrances.add(new MenuEntity("景点", R.mipmap.ic_category_16));
        homeEntrances.add(new MenuEntity("全部分类", R.mipmap.ic_category_15));
        homeEntrances.add(new MenuEntity("美食", R.mipmap.ic_category_0));
        homeEntrances.add(new MenuEntity("电影", R.mipmap.ic_category_1));
        homeEntrances.add(new MenuEntity("酒店住宿", R.mipmap.ic_category_2));
        homeEntrances.add(new MenuEntity("生活服务", R.mipmap.ic_category_3));
        homeEntrances.add(new MenuEntity("KTV", R.mipmap.ic_category_4));
        homeEntrances.add(new MenuEntity("旅游", R.mipmap.ic_category_5));
        homeEntrances.add(new MenuEntity("学习培训", R.mipmap.ic_category_6));
        homeEntrances.add(new MenuEntity("汽车服务", R.mipmap.ic_category_7));
        homeEntrances.add(new MenuEntity("摄影写真", R.mipmap.ic_category_8));
        homeEntrances.add(new MenuEntity("休闲娱乐", R.mipmap.ic_category_10));
        homeEntrances.add(new MenuEntity("丽人", R.mipmap.ic_category_11));
        homeEntrances.add(new MenuEntity("运动健身", R.mipmap.ic_category_12));
        homeEntrances.add(new MenuEntity("大保健", R.mipmap.ic_category_13));
        homeEntrances.add(new MenuEntity("团购", R.mipmap.ic_category_14));
        homeEntrances.add(new MenuEntity("景点", R.mipmap.ic_category_16));
        homeEntrances.add(new MenuEntity("全部分类", R.mipmap.ic_category_15));
        rxView.setData(homeEntrances);
        rxView.initMenuLayout();
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
