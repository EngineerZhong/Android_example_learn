package com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn;

import com.example.a.myapplication12321321.base.mvp.model.BaseModel;
import com.example.a.myapplication12321321.base.mvp.presenter.BasePresenter;
import com.example.a.myapplication12321321.base.mvp.view.BaseView;

/**
 * Time:2018/12/19 - 11:12
 * Author:Zhongwb
 * Description:
 */
public interface PubuContract{


    interface PubuModel extends BaseModel{

    }


    interface PubuView extends BaseView<PubuPresenter>{

    }



    interface PubuPresenter extends BasePresenter{

    }
}
