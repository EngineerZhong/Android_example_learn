package com.example.a.myapplication12321321.rxjava.simple_rx;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2018/12/17 - 10:24
 * Author:Zhongwb
 * Description:
 */
public class ConCreateWatched implements Watched {

    private List<Watcher> watchers = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);

    }

    @Override
    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {
        for(Watcher watcher:watchers){
            watcher.update(str);
        }
    }
}
