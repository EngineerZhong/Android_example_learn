package com.example.a.myapplication12321321.rxjava.rx_download_zrn;

import com.example.a.myapplication12321321.base.mvp.model.BaseModel;
import com.example.a.myapplication12321321.base.mvp.presenter.BasePresenter;
import com.example.a.myapplication12321321.base.mvp.view.BaseView;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail.MenuEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Time:2018/12/18 - 17:35
 * Author:Zhongwb
 * Description:
 */
public interface RxContract {

    interface RxModel extends BaseModel{
        Observable<byte[]> downloadFileFromBytes(String url);
    }

    interface RxView extends BaseView<RxPresenter>{
        void setImage(byte[] bytes);
        void setData(List<MenuEntity> menuEntities);
        void initMenuLayout();
    }

    interface RxPresenter extends BasePresenter{
        void downloadFromBytes(String url);
        void initMenuEntity();
    }

}
