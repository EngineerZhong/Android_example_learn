package com.example.a.myapplication12321321.rxjava.simple_rx;

/**
 * Time:2018/12/17 - 10:31
 * Author:Zhongwb
 * Description:
 */
public class ConCreateWatcher implements Watcher {
    @Override
    public void update(String str) {
        System.out.printf(str);
    }
}
