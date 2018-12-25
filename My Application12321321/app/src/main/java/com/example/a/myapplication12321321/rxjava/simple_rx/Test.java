package com.example.a.myapplication12321321.rxjava.simple_rx;

/**
 * Time:2018/12/17 - 10:31
 * Author:Zhongwb
 * Description:
 */
public class Test {



    public static void main(String[] args){
        Watched Thief = new ConCreateWatched();// 被观察者

        Watcher watcher1 = new ConCreateWatcher(); // 观察者
        Watcher watcher2 = new ConCreateWatcher();

        Thief.addWatcher(watcher1);
        Thief.addWatcher(watcher2);
        Thief.notifyWatchers("我要开始偷了");
    }


}
