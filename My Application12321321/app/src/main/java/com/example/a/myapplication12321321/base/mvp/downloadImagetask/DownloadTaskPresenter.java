package com.example.a.myapplication12321321.base.mvp.downloadImagetask;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.example.a.myapplication12321321.base.okhttputils.Speed;

import butterknife.ButterKnife;

/**
 * Time:2018/11/5 - 15:37
 * Author:Zhongwb
 * Description:
 */
public class DownloadTaskPresenter implements DownloadTaskContract.Presenter {

    private static final String TAG = DownloadTaskPresenter.class.getSimpleName();
    private final DownloadTaskContract.View downloadTaskView;
    private final DownloadTaskContract.Model downLoadTaskModel;

    public DownloadTaskPresenter(DownloadTaskContract.View downloadTaskView,
                                 DownloadTaskContract.Model downloadTaskModel) {
        this.downloadTaskView = downloadTaskView;
        this.downLoadTaskModel = downloadTaskModel;
        this.downloadTaskView.setPresenter(this);
    }


    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
    }

    @Override
    public void onStart() {
        Log.i(TAG,"onStart");
    }

    @Override
    public void onResume() {
        Log.i(TAG,"onResume");
    }

    @Override
    public void onPause() {
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Log.i(TAG,"onStateChanged---" + event);
    }

    @Override
    public void downloadImgFromNet() {
        downloadTaskView.showProgressDialog();
        downLoadTaskModel.downloadImgModelFromFile(new DownloadTaskModel.dataCallBack<Bitmap>() {
            @Override
            public void onSpeedResult(final Speed speed) {
                ((DownloadTaskActivity) downloadTaskView).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadTaskView.setImgSpeed(speed);
                    }
                });
            }

            @Override
            public void onImgResult(Bitmap obj) {
                downloadTaskView.setImgPic(obj);
                downloadTaskView.closeProgressDialog();
            }
        });
    }

    @Override
    public void downloadImgFromByte() {
        downloadTaskView.showProgressDialog();
        downLoadTaskModel.downloadImgModelFromStream(new DownloadTaskModel.dataCallBack<byte[]>() {
            @Override
            public void onSpeedResult(final Speed speed) {
                ((DownloadTaskActivity) downloadTaskView).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        downloadTaskView.setImgSpeed(speed);
                    }
                });
            }

            @Override
            public void onImgResult(byte[] bytes) {
                try {
                    downloadTaskView.setImgPic(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    downloadTaskView.closeProgressDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                    downloadTaskView.closeProgressDialog();
                }
            }
        });
    }

}
