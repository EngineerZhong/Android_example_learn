package com.example.a.myapplication12321321.rxjava.obervable_rx;

import java.util.Observable;
import java.util.Observer;

/**
 * Time:2018/12/17 - 10:57
 * Author:Zhongwb
 * Description:
 */
public class SimpleObserver implements Observer {


    public SimpleObserver(SimpleObservable observable){
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("data is changed: " + ((SimpleObservable)observable).getData());
    }
}
