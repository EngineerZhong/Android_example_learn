package com.example.a.myapplication12321321;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication12321321.base.eventbusutils.BindEventBus;
import com.example.a.myapplication12321321.base.eventbusutils.Event;
import com.example.a.myapplication12321321.base.eventbusutils.EventBusUtils;
import com.example.a.myapplication12321321.base.eventbusutils.EventCode;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.mvp.MyApplication;
import com.example.a.myapplication12321321.base.okhttputils.OkHttpManager;
import com.example.a.myapplication12321321.base.okhttputils.RequestCallback;
import com.example.a.myapplication12321321.base.okhttputils.Speed;
import com.example.a.myapplication12321321.base.pictureselector.PictureSelectorUtil;
import com.example.a.myapplication12321321.base.retrofitutils.HttpObserver;
import com.example.a.myapplication12321321.base.retrofitutils.bean.ResultBean;
import com.example.a.myapplication12321321.base.retrofitutils.RetrofitManager;
import com.example.a.myapplication12321321.base.retrofitutils.ServerApi;
import com.example.a.myapplication12321321.base.retrofitutils.bean.User;
import com.example.a.myapplication12321321.bean.ResponseGeneralUser;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@BindEventBus
public class SecondActivity extends BaseActivity {
    private final String TAG = SecondActivity.class.getSimpleName();
    @BindView(R.id.tv_sec_main)
    TextView tvSecMain;
    @BindView(R.id.pgb_loading)
    ProgressBar pgbLoading;
    @BindView(R.id.btn_send_ok_http)
    Button btnSendOkHttp;
    @BindView(R.id.btn_download_ok_http)
    Button btnDownloadOkHttp;
    @BindView(R.id.pgb_downloading)
    ProgressBar pgbDownloading;
    @BindView(R.id.tv_downloadingProgress)
    TextView tvDownloadingProgress;
    @BindView(R.id.tv_downloadingSpeed)
    TextView tvDownloadingSpeed;
    @BindView(R.id.btn_upload_ok_http)
    Button btnUploadOkHttp;
    @BindView(R.id.btn_retrofit_ok_http)
    Button btnRetrofitOkHttp;
    @BindView(R.id.tv_textContent)
    TextView tvTextContent;

    private Uri imageUri;
    private static final int REQUEST_ALBUM_OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    protected void initView(View mContenxt) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected View bindView() {
        return null;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {

    }


    @OnClick(R.id.btn_retrofit_ok_http)
    public void btnRetrofitTest() {
        RetrofitManager
                .getInstance()
                .create(ServerApi.class)
                .queryTestCallBack("queryAll", new retrofit2.Callback<ResultBean<List<User>>>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResultBean<List<User>>> call, retrofit2.Response<ResultBean<List<User>>> response) {

                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResultBean<List<User>>> call, Throwable t) {

                    }
                });
        queryAll();
    }

    public void queryAll(){
        RetrofitManager
                .getInstance()
                .create(ServerApi.class)
                .queryAll("queryAll")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResultBean<List<User>>>() {
                    @Override
                    public void onSuccess(ResultBean<List<User>> listResultBean) {

                    }

                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void complete() {

                    }
                });
    }


    public void loginGetUserInfo(){
        RetrofitManager
                .getInstance()
                .create(ServerApi.class)
                .loginGetUserInfo("loginGetUserInfo","dalididilo", "DALIDIDILO")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResultBean<User>>() {
                    @Override
                    public void onSuccess(ResultBean<User> userResultBean) {
                        if(userResultBean.getStatueCode()==200 && userResultBean.getResult() != null){
                            tvTextContent.setText(userResultBean.getResult().getToken());
                        }else{
                            tvTextContent.setText(userResultBean.getDescribe());
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void complete() {

                    }
                });
    }


    @OnClick(R.id.btn_download_ok_http)
    public void btnDownload() {
        String url = "http://58.63.233.48/app.znds.com/down/20170712/ystjg_2.6.0.1059_dangbei.apk";
        OkHttpManager.downloadAsyn(url, Environment.getExternalStorageDirectory().getAbsolutePath(), new RequestCallback<Object>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i("####", e.getMessage());
            }

            @Override
            public void onResponse(Object response) {

            }
        }, new OkHttpManager.LoadListener() {
            @Override
            public void onloading(final Speed speed) {
                SecondActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (speed.getProgress() < 0) {
                            return;
                        }
                        pgbDownloading.setProgress(speed.getProgress());
                        tvDownloadingProgress.setText(speed.getProgress() + "%");
                        tvDownloadingSpeed.setText(speed.getSpeed());

                        if (pgbDownloading.getProgress() == 100) {
                            pgbDownloading.setProgress(0);
                            tvDownloadingProgress.setText("0%");
                            tvDownloadingSpeed.setText("0.00M/0.00M");
                        }
                    }
                });
            }
        });
    }


    @OnClick(R.id.btn_upload_ok_http)
    public void btnUploadFile() {
//        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
//        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        startActivityForResult(albumIntent, REQUEST_ALBUM_OK);
        PictureSelector.create(SecondActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(9)
                .minSelectNum(1)
                .isCamera(true)
                .selectionMode(PictureConfig.MULTIPLE)
                .minimumCompressSize(100)
                .compress(true)
                .compressSavePath(PictureSelectorUtil.getPath("zImage"))
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private String photoPath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ALBUM_OK:
                Log.d(TAG, "onActivityResult:相册 " + data.getData().toString());
                ContentResolver resolver = getContentResolver();
                try {
                    imageUri = data.getData();
                    //获取照片路径
                    String[] filePathColumn = {MediaStore.Audio.Media.DATA};
                    Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    photoPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                    cursor.close();
                    OkHttpManager.postAsyn(MyApplication.getServiceAddress()+"/UploadFile", new RequestCallback<Object>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.d(TAG, "####file" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Object response) {
                            Log.d(TAG, "####file");
                        }
                    }, new File(photoPath), "file", new OkHttpManager.LoadListener() {
                        @Override
                        public void onloading(final Speed speed) {
                            SecondActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pgbDownloading.setProgress(speed.getProgress());
                                    tvDownloadingProgress.setText(speed.getProgress() + "%");
                                    tvDownloadingSpeed.setText(speed.getSpeed());
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PictureConfig.CHOOSE_REQUEST:
                if (data == null) {
                    break;
                }
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                OkHttpManager.postAsyn(MyApplication.getServiceAddress()+"/UploadFile", new RequestCallback<Object>() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(Object response) {
                                PictureFileUtils.deleteCacheDirFile(SecondActivity.this);
                            }


                        }, PictureSelectorUtil.getFiles(selectList, true), PictureSelectorUtil.getFilesKey(selectList)
                        , new OkHttpManager.LoadListener() {
                            @Override
                            public void onloading(final Speed speed) {
                                SecondActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pgbDownloading.setProgress(speed.getProgress());
                                        tvDownloadingProgress.setText(speed.getProgress() + "%");
                                        tvDownloadingSpeed.setText(speed.getSpeed());
                                    }
                                });
                            }
                        }, new OkHttpManager.Param("username", "Dalididilo"));
                break;
        }
    }

    @OnClick(R.id.btn_send_ok_http)
    public void btnOkHttp() {
        pgbLoading.setVisibility(View.VISIBLE);
        sendOkHttpPlus();
    }

    public void sendOkHttpPlus() {
        OkHttpManager.postAsyn(MyApplication.getServiceAddress()+"/RetrofitGet", new RequestCallback<ResponseGeneralUser<User>>() {
            @Override
            public void onError(Request request, Exception e) {
                SecondActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getMyContext(), "出错了", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(final ResponseGeneralUser<User> response) {
                SecondActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSecMain.setText(response.getResult().getToken());
                        pgbLoading.setVisibility(View.GONE);
                    }
                });

            }
        }, new OkHttpManager.Param[]{new OkHttpManager.Param("method", "loginGetUserInfo")
                ,new OkHttpManager.Param("username", "dalididilo0")
                , new OkHttpManager.Param("password", "DALIDIDILO")});
    }

    /**
     * 未封装的OKHTTP请求
     */
    public void sendOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "dalididilo0")
                .add("password", "DALIDIDILO")
                .build();
        Request request = new Request.Builder().url(MyApplication.getServiceAddress()+"/RetrofitGet").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBusUtils.postEvent(new Event<String>(500, e.getMessage().toString()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                EventBusUtils.postEvent(new Event<String>(200, response.body().string()));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(Event<Integer> msg) {
        if (msg.getCode() == EventCode.LOGIN) {
            Log.i(TAG, Thread.currentThread().getName() + "#### onMessageEvent(Event<Integer> msg)");
            Toast.makeText(getApplicationContext(), msg.getCode() + " " + msg.getData(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
