package com.example.a.myapplication12321321;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a.myapplication12321321.base.mvp.ActivityCollector;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;

public class ThirdActivity extends BaseActivity {


    private TextView tvContent;
    private Button btnClick;
    private Button btnFinishAll;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvContent.setText(getIntent().getStringExtra("extra_content"));
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void setListener() {
        btnClick.setOnClickListener(this);
        btnFinishAll.setOnClickListener(this);
    }

    @Override
    protected void initView(View mContext) {
        btnClick = mContext.findViewById(R.id.btn_click);
        tvContent = mContext.findViewById(R.id.tv_textContent);
        btnFinishAll = mContext.findViewById(R.id.btn_finishAll);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_third;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(this).inflate(bindLayout(), null);
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                Intent intent = new Intent();
                intent.putExtra("data_return", "hello world");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btn_finishAll:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getMyContext())
                        .setTitle("提醒")
                        .setMessage("你现在的操作将会退出程序，你确定吗？")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCollector.finishAll();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCollector.printActivity();
                            }
                        });
                dialog.show();
                break;
        }
    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
