package com.example.a.myapplication12321321;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myapplication12321321.base.eventbusutils.BindEventBus;
import com.example.a.myapplication12321321.base.eventbusutils.Event;
import com.example.a.myapplication12321321.base.eventbusutils.EventBusUtils;
import com.example.a.myapplication12321321.base.eventbusutils.EventCode;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.mvp.downloadImagetask.DownloadTaskActivity;
import com.example.a.myapplication12321321.base.permissiongrantutils.PermissionUtils;
import com.example.a.myapplication12321321.base.viewpagerfragment.ViewPageActivity;
import com.example.a.myapplication12321321.broadcast.MyBroadcast;
import com.example.a.myapplication12321321.designview.DesignActivity;
import com.example.a.myapplication12321321.rxjava.CommonActivity_Fragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


@BindEventBus
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_textContent)
    TextView tvTextContent;
    @BindView(R.id.btn_sendMain)
    Button btnSendMain;
    @BindView(R.id.btn_sendBackGroundThread)
    Button btnSendBackGroundThread;
    @BindView(R.id.btn_sendBackForceThread)
    Button btnSendBackForceThread;
    @BindView(R.id.btn_sendSameThread)
    Button btnSendSameThread;
    @BindView(R.id.btn_goto)
    Button btnGoto;
    @BindView(R.id.btn_greedao)
    Button btnGreedao;
    @BindView(R.id.btn_rxjava)
    Button btnRxjava;
    Button btnMezuRc;
    Button btnBroadcast;
    Button btnStorage;
    @BindView(R.id.btn_rxandroid)
    Button btnRxandroid;
    @BindView(R.id.btn_designview_main_activity)
    Button btnDesignviewMainActivity;

    private LocalBroadcastManager localManager;
    private LocalRecevier localRecevier;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.remove_item:
                startActivity(DialogFragment.class);
                break;
            case R.id.add_item:
                intent = new Intent("com.example.a.myapplication12321321.ThirdActivityAction");
                intent.addCategory("com.example.a.myapplication12321321.ThirdCategory");
                intent.putExtra("extra_content", "IntentExtraData");
                startActivityForResult(intent, 1);
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    tvTextContent.setText(data.getStringExtra("data_return"));
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission(new PermissionUtils.PermissionGrant() {
                @Override
                public void onPermissionGranted(int requestCode) {
                    Log.i(TAG, requestCode + "onPermissionGranted");
                }

                @Override
                public void onFail() {
                    Log.i(TAG, "onFail");
                }
            }, new int[]{
                    PermissionUtils.CODE_READ_EXTERNAL_STORAGE,
                    PermissionUtils.CODE_AUDIO,
                    PermissionUtils.CODE_CAMERA,
                    PermissionUtils.CODE_LOCATION,
                    PermissionUtils.CODE_PHONE
            });
        }
    }

    @Override
    protected void requestPermission(PermissionUtils.PermissionGrant permissionGrant, int[] requestCode) {
        super.requestPermission(permissionGrant, requestCode);
    }

    @Override
    protected void doBusiness(Context mContext) {

        registerLocalReceiver();
    }

    @Override
    protected void setListener() {
        btnMezuRc.setOnClickListener(this);
        btnRxandroid.setOnClickListener(this);
        btnDesignviewMainActivity.setOnClickListener(this);
        btnBroadcast.setOnClickListener(this);
        btnStorage.setOnClickListener(this);
    }

    @Override
    protected void initView(View mContenxt) {
        btnMezuRc = mContenxt.findViewById(R.id.btn_mezuRc);
        btnRxandroid = mContenxt.findViewById(R.id.btn_rxandroid);
        btnDesignviewMainActivity = mContenxt.findViewById(R.id.btn_designview_main_activity);
        btnBroadcast = mContenxt.findViewById(R.id.btn_broadcast_main_activity);
        btnStorage = mContenxt.findViewById(R.id.btn_storage_main_activity);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(getMyContext()).inflate(bindLayout(), null);
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mezuRc:
                startActivity(MezuRecycleView.class);
                break;
            case R.id.btn_rxandroid:
                startActivity(CommonActivity_Fragment.class);
                break;
            case R.id.btn_designview_main_activity:
                startActivity(DesignActivity.class);
                break;
            case R.id.btn_broadcast_main_activity:
//              发送广播
//                startBroadcastReceiver();
//              发送本地广播
                sendLocalBroadcast();
                break;
            case R.id.btn_storage_main_activity:
                try {
                    testStorage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

//    文件存储技术。
//    Context.MODE_PRIVATE : 存在重名文件，则覆盖内容
//    Context.MODE_APPEND : 存在重名文件，追加内容。
    private FileOutputStream out = null;
    private BufferedWriter writer = null;
    private FileInputStream in = null;
    private BufferedReader reader = null;
    @SuppressLint("CheckResult")
    private void testStorage() throws FileNotFoundException {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                out = getMyContext().openFileOutput("data",Context.MODE_APPEND);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                writer.write((String) btnStorage.getText());
                if(writer != null){
                    writer.close();
                }
                emitter.onNext("yes");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        StringBuilder builder = new StringBuilder();
                        if("yes".equals(s)){
                            in = getMyContext().openFileInput("data");
                            reader = new BufferedReader(new InputStreamReader(in));
                            String line = "";
                            while((line = reader.readLine()) != null){
                                builder.append(line);
                            }
                            showToastShort(builder.toString());
                            if(reader != null){
                                reader.close();
                            }
                        }
                    }
                });
    }

    //    注册本地广播接收器
    private void registerLocalReceiver() {
        localManager = LocalBroadcastManager.getInstance(getMyContext());
        localRecevier = new LocalRecevier();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.a.myapplication");
        localManager.registerReceiver(localRecevier, filter);
    }

    //    本地广播
    private void sendLocalBroadcast() {
        Intent intent = new Intent("com.example.a.myapplication");
        localManager.sendBroadcast(intent);
    }

    class LocalRecevier extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("提示")
                    .setCancelable(true)
                    .setMessage("LocalBroadcast");
            builder.create().show();
        }
    }

    private MyBroadcast broadcast;

    //      动态注册BroadcastReceiver
    private void startBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        broadcast = new MyBroadcast();
        registerReceiver(broadcast, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
//      动态注册的广播接收器一定要取消注册。
        unregisterReceiver(broadcast);
//      取消注册
        localManager.unregisterReceiver(localRecevier);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(Event<String> msg) {
        if (msg.getCode() == EventCode.CHECK) {
            Log.i(TAG, Thread.currentThread().getName() + "#### onMainEventBus(Message msg)");
            tvTextContent.setText(msg.getData());
        }
    }


    @OnClick({R.id.btn_sendMain
            , R.id.btn_sendBackGroundThread
            , R.id.btn_sendBackForceThread
            , R.id.btn_goto
            , R.id.btn_sendSameThread
            , R.id.btn_greedao
            , R.id.btn_rxjava})
    protected void widgetOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sendMain:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            EventBusUtils.postEvent(new Event<String>(EventCode.CHECK, "CHECK-EVENT"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                startActivity(new Intent(this, ViewPageActivity.class));
                break;
            case R.id.btn_sendSameThread:
                startActivity(new Intent(this, MultipleRecyclerActivity.class));
                break;
            case R.id.btn_goto:
//                EventBus.getDefault().postSticky(new Message("404","Not Found Page!"));
                EventBusUtils.postStickyEvent(new Event<Integer>(EventCode.LOGIN, 404));
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn_sendBackForceThread:
                startActivity(new Intent(this, DownloadTaskActivity.class));
                break;
            case R.id.btn_sendBackGroundThread:
                startActivity(new Intent(this, RecyclerActivity.class));
                break;
            case R.id.btn_greedao:
                startActivity(new Intent(this, GreenDaoActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this, RxJava_Activity.class));
                break;

        }
    }


    @Override
    public Context getMyContext() {
        return this;
    }
}
