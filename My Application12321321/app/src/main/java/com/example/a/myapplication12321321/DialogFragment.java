package com.example.a.myapplication12321321;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a.myapplication12321321.base.mvp.BaseActivity;

public class DialogFragment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView(View mContext) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_dialog_fragment;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(this).inflate(bindLayout(),null);
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
