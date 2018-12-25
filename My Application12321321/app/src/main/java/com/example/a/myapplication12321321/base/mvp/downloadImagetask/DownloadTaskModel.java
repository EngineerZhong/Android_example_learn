package com.example.a.myapplication12321321.base.mvp.downloadImagetask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.a.myapplication12321321.base.okhttputils.OkHttpManager;
import com.example.a.myapplication12321321.base.okhttputils.RequestCallback;
import com.example.a.myapplication12321321.base.okhttputils.Speed;


import okhttp3.Request;

/**
 * Time:2018/11/5 - 15:42
 * Author:Zhongwb
 * Description:
 */
public class DownloadTaskModel implements DownloadTaskContract.Model {

    private String url
            = "http://img2.ph.126.net/zPcCwsW3ca6xiMVZazROJA==/6631560346909454171.jpg";

    @Override
    public void downloadImgModelFromFile(final DownloadTaskModel.dataCallBack<Bitmap> dataCallBack) {
        OkHttpManager.downloadAsyn(url, Environment.getExternalStorageDirectory().getAbsolutePath(), new RequestCallback<Object>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
             public void onResponse(Object response) {
                dataCallBack.onImgResult(BitmapFactory.decodeFile(response.toString()));
            }
        }, new OkHttpManager.LoadListener() {
            @Override
            public void onloading(Speed speed) {
                dataCallBack.onSpeedResult(speed);
            }
        });
    }

    @Override
    public void downloadImgModelFromStream(final DownloadTaskModel.dataCallBack<byte[]> dataCallBack) {
        OkHttpManager.downloadStreamAsyn(url, new RequestCallback<byte[]>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(byte[] bytes) {
                dataCallBack.onImgResult(bytes);
            }
        }, new OkHttpManager.LoadListener() {
            @Override
            public void onloading(Speed speed) {
                dataCallBack.onSpeedResult(speed);
            }
        });
    }


    interface dataCallBack<T>{
        void onSpeedResult(Speed speed);
        void onImgResult(T obj);
    }

}
