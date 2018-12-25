package com.example.a.myapplication12321321.base.mvp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a.myapplication12321321.base.greendao.database.DaoMaster;
import com.example.a.myapplication12321321.base.greendao.database.DaoSession;

/**
 * Time:2018/11/12 - 14:39
 * Author:Zhongwb
 * Description:
 */
public class MyApplication extends BaseApplication {

    public static Context mAppContext;
    private static final String TAG = MyApplication.class.getSimpleName();

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static final String SERVICE_ADDRESS = "http://192.168.1.138:8080/Retrofit/";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDataBase();
    }

    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public static String getServiceAddress() {
        return SERVICE_ADDRESS;
    }

    /**
     * 设置数据库
     */
    public void setDataBase(){
        mHelper = new DaoMaster.DevOpenHelper(this, "example.db", null);
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
