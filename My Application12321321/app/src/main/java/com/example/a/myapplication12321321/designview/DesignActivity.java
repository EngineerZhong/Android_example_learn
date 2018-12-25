package com.example.a.myapplication12321321.designview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;

public class DesignActivity extends BaseActivity {

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
        return R.layout.activity_design;
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
