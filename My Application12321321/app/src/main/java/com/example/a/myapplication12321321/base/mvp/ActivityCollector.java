package com.example.a.myapplication12321321.base.mvp;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2018/12/13 - 17:01
 * Author:Zhongwb
 * Description:
 */
public class ActivityCollector {
    private static final String TAG = "ActivityCollector";
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity :activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }

    public static void printActivity(){
        for(Activity activity :activities) {
            Log.i(TAG,"Activity:->" + activity);
        }
    }
}
