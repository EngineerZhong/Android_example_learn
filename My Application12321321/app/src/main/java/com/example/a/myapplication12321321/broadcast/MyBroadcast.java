package com.example.a.myapplication12321321.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Time:2018/12/24 - 10:19
 * Author:Zhongwb
 * Description: 广播接收器继承BroadcastReceiver 重写onReceive()方法
 */
public class MyBroadcast extends BroadcastReceiver {
    public MyBroadcast() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable()){
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setCancelable(true)
                    .setMessage("\r\n当前网络: " + networkInfo.getDetailedState()
                            + "\r\n网络类型" + networkInfo.getTypeName()
                            + "\r\n是否可用:" + (networkInfo.isAvailable()?"是":"否"));
            builder.create().show();
        }else{
            Toast.makeText(context,"network error",Toast.LENGTH_SHORT).show();
        }
    }
}
