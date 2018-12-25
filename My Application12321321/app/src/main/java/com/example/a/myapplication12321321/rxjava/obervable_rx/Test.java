package com.example.a.myapplication12321321.rxjava.obervable_rx;

/**
 * Time:2018/12/17 - 11:00
 * Author:Zhongwb
 * Description:
 */
public class Test {
    public static void main(String[] args){

        SimpleObservable observable = new SimpleObservable();

        SimpleObserver observer = new SimpleObserver(observable);

        observable.setData(1);
        observable.setData(5);
        observable.setData(5);
        observable.setData(6);

    }
}
