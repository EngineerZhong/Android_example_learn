package com.example.a.myapplication12321321.rxjava.simple_rx;

/**
 * Time:2018/12/17 - 10:24
 * Author:Zhongwb
 * Description:
 */
public interface Watched {

    void addWatcher(Watcher watcher);
    void removeWatcher (Watcher watcher);
    void notifyWatchers(String str);

}
