package com.example.a.myapplication12321321.base.mvp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {

    /**
     * @Description:全局引用
     * @author:zach
     * @time:2014-12-9 上午10:40:03
     */

    public List<Activity> activityList = new ArrayList<Activity>();
    private static BaseApplication instance;

    public BaseApplication() {

    }

    public static BaseApplication getInstance(Context context) {
        if (null == instance) {
            instance = (BaseApplication) context.getApplicationContext();
        }

        return instance;

    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public boolean containActivity(String className) {

        for (int i = 0; i < activityList.size(); i++) {
            if (activityList.get(i).getClass().getName().equals(className)) {
                return true;
            }

        }

        return false;

    }

    public void exit() {

        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();

        // System.exit(0);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // CrashHandler crashHandler = CrashHandler.getInstance();
        // crashHandler.init(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
