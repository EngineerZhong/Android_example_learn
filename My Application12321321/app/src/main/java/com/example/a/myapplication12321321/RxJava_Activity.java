package com.example.a.myapplication12321321;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a.myapplication12321321.base.greendao.database.DaoSession;
import com.example.a.myapplication12321321.base.greendao.database.UserDao;
import com.example.a.myapplication12321321.base.greendao.entity.User;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.mvp.MyApplication;
import com.example.a.myapplication12321321.base.okhttputils.OkHttpManager;
import com.example.a.myapplication12321321.base.okhttputils.RequestCallback;
import com.example.a.myapplication12321321.base.okhttputils.Speed;
import com.example.a.myapplication12321321.base.retrofitutils.bean.ResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJava_Activity extends BaseActivity {

    @BindView(R.id.btn_rxjava_simple)
    Button btnRxjavaSimple;
    @BindView(R.id.btn_rxjava_better)
    Button btnRxjavaBetter;
    @BindView(R.id.grid_recyclerview)
    RecyclerView gridRecyclerview;
    @BindView(R.id.btn_rxjava_map)
    Button btnRxjavaMap;
    @BindView(R.id.btn_rxjava_concat)
    Button btnRxjavaConcat;
    @BindView(R.id.img_concat_img)
    ImageView imgConcatImg;

    private Disposable mDisposable;

    private RxJavaAdapter adapter;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.btn_rxjava_simple, R.id.btn_rxjava_better, R.id.btn_rxjava_map, R.id.btn_rxjava_concat})
    public void widgetOnClick(View view) {
        widgetClick(view);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView(View mContenxt) {
        ButterKnife.bind(this);
        adapter = new RxJavaAdapter(bitmaps, R.layout.rv_img_item, getMyContext());
        adapter.setEmptyView(R.layout.item_no_data_small_layout);
        adapter.setGridLayoutManager(gridRecyclerview);

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_rx_java_;
    }

    @Override
    protected View bindView() {
        View main = LayoutInflater.from(getMyContext()).inflate(R.layout.activity_rx_java_, null);
        return main;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava_simple:
                rxJavaSimple();
                break;
            case R.id.btn_rxjava_better:
                rxJavaBetter();
                break;
            case R.id.btn_rxjava_map:
                rxJavaMap();
                break;
            case R.id.btn_rxjava_concat:
                rxJavaConcat();
                break;
        }
    }

    /**
     * RxJava Concat操作符应用
     * 业务描述
     * 1.读取图片，先从本地缓存中读取，本地存在调用OnNext();则不从网络上获取
     * 2.若本地不存在图片，则从网络上获取。663156034690945417
     */
    @SuppressLint("CheckResult")
    private void rxJavaConcat() {
        Observable<Bitmap> cache = Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                Log.i(TAG, Thread.currentThread().getName());
                Bitmap bitmap = BitmapFactory.decodeFile(getMyContext().getSharedPreferences("imgPath", 0).getString("img", ""));
                if (bitmap == null) {
                    emitter.onComplete();
                    Log.i(TAG, "emitter.onComplete()");
                } else {
                    emitter.onNext(bitmap);
                    Log.i(TAG, "emitter.onNext(bitmap)");
                }
            }
        });
        Observable<Bitmap> network = Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                downloadFileSavePathInSP();
            }
        });

        Observable.concat(cache, network).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        imgConcatImg.setImageBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, throwable.getMessage());
                    }
                });
    }

    private void downloadFileSavePathInSP() {
        String url = "http://img1.ph.126.net/nBqg6q4lta9rr4sql1tgww==/2211267417138951172.jpg";
        OkHttpManager.downloadAsyn(url, Environment.getExternalStorageDirectory().getPath(), new RequestCallback<File>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(final File response) {
                SharedPreferences sharedPreferences = getMyContext().getSharedPreferences("imgPath", 0);
                sharedPreferences.edit().putString("img", response.getAbsolutePath()).commit();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgConcatImg.setImageBitmap(BitmapFactory.decodeFile(response.getAbsolutePath()));
                    }
                });
            }
        }, null);
    }

    /**
     * RxJava.Map操作符的应用
     * 业务描述
     * 1.通过Okhttp请求服务获取Json数据
     * 2.通过map将json数据转为实体类型
     * 3.通过doOnNext，解析Bean中的数据，存入到Android本地数据库
     * 4.耗时线程在子线程进行，在主线程中弹出Toast
     */
    @SuppressLint("CheckResult")
    private void rxJavaMap() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(final ObservableEmitter<Response> emitter) throws Exception {
                postParams(MyApplication.getServiceAddress() + "RetrofitGet", new CallBack<Response>() {
                    @Override
                    public void onResult(Response result) {
                        emitter.onNext(result);
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Response, ResultBean<User>>() {
                         @Override
                         public ResultBean<User> apply(Response response) throws Exception {
                             if (response.isSuccessful()) {
                                 ResponseBody body = response.body();
                                 if (body != null) {
                                     Type type = new TypeToken<ResultBean<User>>() {}.getType();
                                     return new Gson().fromJson(body.string(), type);
                                 }
                             }
                             return null;
                         }
                     }
                ).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<ResultBean<User>>() {
                    @Override
                    public void accept(ResultBean<User> user) throws Exception {
                        daoSession = MyApplication.getApplication(getMyContext()).getDaoSession();
                        daoSession.insertOrReplace(user.getResult());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultBean<User>>() {
                    @Override
                    public void accept(ResultBean<User> user) throws Exception {
                        showToastShort((daoSession.queryBuilder(User.class).where(UserDao.Properties.Id.eq(user.getResult().getId())).build()).unique().toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToastShort(throwable.getMessage());
                    }
                });
    }

    private void postParams(String url, final CallBack callBack) {
        OkHttpManager.postAsyn(url, new RequestCallback<Response>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response result) {
                callBack.onResult(result);
            }
        }, new OkHttpManager.Param("method", "rxjavaMap"));

    }

    /**
     * RxJava，下载图片。
     * 并将下载好的图片发送到主线程更新UI
     * PS:由于Okhttp异步下载图片的过程，内部新开线程，OnNext()发送无法成功。
     * 解决方法是，在类内部定义一个数据返回接口，将数据返回到subscribeOn执行的线程中
     * 被订阅者onNext更新状态返回到订阅者订阅回调线程中，更新UI
     */
    private void rxJavaBetter() {

        Observable.create(new ObservableOnSubscribe<Bitmap>() {

            @Override
            public void subscribe(final ObservableEmitter<Bitmap> emitter) throws Exception {
                String[] imgsUrl = {
                        "https://upload-images.jianshu.io/upload_images/11181600-db48df38b76ed467.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-f9cc67a96b58d49e.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-4860ecb6acb8fce5.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-a5db9d1018924867.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-d2131192b602b425.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-e3002c837b5335f9.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-2ddfaaa766a399e5.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-d58b814b598ea3d3.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-8e2e151505cf6899.jpg",
                        "https://upload-images.jianshu.io/upload_images/11181600-a535283f3402accb.jpg"
                };
                for (int i = 0; i < imgsUrl.length; i++) {
                    download(imgsUrl[i], new CallBack<Bitmap>() {
                        @Override
                        public void onResult(Bitmap bitmap) {
                            emitter.onNext(bitmap);
                        }
                    });
                }
//             emitter.onNext(BitmapFactory.decodeResource(getResources(), R.mipmap.zwb));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        Log.i(TAG, "onNext");
                        adapter.addData(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }

    private void download(String url, final CallBack callBack) {
        OkHttpManager.downloadStreamAsyn(url, new RequestCallback<byte[]>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(byte[] bytes) {
                callBack.onResult(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        }, new OkHttpManager.LoadListener() {
            @Override
            public void onloading(Speed speed) {

            }
        });
    }

    /**
     * RxJavaSimple
     */
    private void rxJavaSimple() {
        /**
         * 被观察者
         */
        Observable novel = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("更新5-9");
                emitter.onNext("更新9-15");
                emitter.onComplete();
            }
        });
        /**
         * 观察者
         */
        Observer<String> reader = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.i(TAG, "onSubscribe");

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
                Log.i(TAG, "onNext");
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };
        // 被动句，小说被读者观察。读者是 观察者，,
        novel.subscribe(reader);
    }

    @Override
    public Context getMyContext() {
        return this;
    }


    interface CallBack<T> {
        void onResult(T result);
    }
}
