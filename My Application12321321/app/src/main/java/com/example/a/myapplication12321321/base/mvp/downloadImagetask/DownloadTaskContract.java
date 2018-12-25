package com.example.a.myapplication12321321.base.mvp.downloadImagetask;

import android.graphics.Bitmap;
import com.example.a.myapplication12321321.base.mvp.model.BaseModel;
import com.example.a.myapplication12321321.base.mvp.presenter.BasePresenter;
import com.example.a.myapplication12321321.base.mvp.view.BaseView;
import com.example.a.myapplication12321321.base.okhttputils.Speed;


/**
 * Time:2018/11/5 - 15:20
 * Author:Zhongwb
 * Description:
 */
interface DownloadTaskContract {

    /**
     * 业务逻辑处理Model层
     */
    interface Model extends BaseModel{
        void downloadImgModelFromFile(DownloadTaskModel.dataCallBack<Bitmap> dataCallBack);
        void downloadImgModelFromStream(DownloadTaskModel.dataCallBack<byte[]> dataCallBack);
    }

    /**
     * 视图View接口
     */
    interface View extends BaseView<Presenter>{
        void setImgPic(Bitmap bitmap);
        void setImgSpeed(Speed speed);
        void showProgressDialog();
        void closeProgressDialog();
    }

    /**
     * 通过Presenter将数据返回到View层进行展示。
     */
    interface Presenter extends BasePresenter{
        void downloadImgFromNet();
        void downloadImgFromByte();
    }
}

