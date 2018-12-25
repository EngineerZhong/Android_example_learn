package com.example.a.myapplication12321321.base.mvp.downloadImagetask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.okhttputils.Speed;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadTaskActivity extends BaseActivity implements DownloadTaskContract.View {

    @BindView(R.id.pgb_downloadingImg)
    ProgressBar pgbDownloadingImg;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.img_download)
    ImageView imgDownload;
    @BindView(R.id.btn_downloadTask)
    Button btnDownloadTask;
    @BindView(R.id.btn_downloadfromstreamTask)
    Button btnDownloadfromstreamTask;
    @BindView(R.id.btn_onclick)
    Button btnOnclick;
    @BindView(R.id.pb_bar)
    ProgressBar pbBar;

    private DownloadTaskContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBusiness(Context mContext) {
        new DownloadTaskPresenter(this, new DownloadTaskModel());
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_downloadTask:
                mPresenter.downloadImgFromByte();
                break;
            case R.id.btn_downloadfromstreamTask:
                mPresenter.downloadImgFromNet();
                break;
            case R.id.btn_onclick:
                showToastShort("btn_onclick Test");
                break;
        }
    }

    @Override
    protected void setListener() {
        btnOnclick.setOnClickListener(this);
        btnDownloadfromstreamTask.setOnClickListener(this);
        btnDownloadTask.setOnClickListener(this);
    }

    @Override
    protected void initView(View mContenxt) {
        ButterKnife.bind(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_download_task;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(this).inflate(bindLayout(), null);
    }

    @Override
    protected void initParams(Bundle bundle) {

    }


    @Override
    public void setImgPic(Bitmap bitmap) {
        imgDownload.setImageBitmap(bitmap);
    }

    @Override
    public void setImgSpeed(Speed speed) {
        tvLoading.setText(speed.getSpeed());
        pgbDownloadingImg.setProgress(speed.getProgress());
    }

    @Override
    public void showProgressDialog() {
        pbBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    @Override
    public void closeProgressDialog() {
        pbBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void setPresenter(DownloadTaskContract.Presenter presenter) {
        if (presenter == null) {
            throw new NullPointerException();
        }
        mPresenter = presenter;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
