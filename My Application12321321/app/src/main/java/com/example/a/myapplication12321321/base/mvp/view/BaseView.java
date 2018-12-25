package com.example.a.myapplication12321321.base.mvp.view;

import android.content.Context;

/**
 * Time:2018/11/5 - 15:14
 * Author:Zhongwb
 * Description:
 */
public interface BaseView<T> {


    void setPresenter(T presenter);

    void showError(String error);

    Context getMyContext();


}
