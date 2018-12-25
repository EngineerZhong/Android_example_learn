package com.example.a.myapplication12321321.rxjava;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.mvp.BaseFragment;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.RxFragment;
import com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.PubuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2018/12/19 - 10:39
 * Author:Zhongwb
 * Description:
 */
public class CommonActivity_Fragment extends BaseActivity {
    @BindView(R.id.ll_frame)
    FrameLayout llFrameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void doBusiness(Context mContext) {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView(View mContext) {
       initFragmentManager();
       BaseFragment fragment = new RxFragment();
       addFragment(fragment,R.id.ll_frame,RxFragment.class.getSimpleName(),false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_common_fragment;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(getMyContext()).inflate(bindLayout(),null);
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
