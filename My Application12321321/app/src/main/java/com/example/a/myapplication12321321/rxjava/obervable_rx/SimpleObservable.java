package com.example.a.myapplication12321321.rxjava.obervable_rx;


import java.util.Observable;
import java.util.Observer;

/**
 * Time:2018/12/17 - 10:52
 * Author:Zhongwb
 * Description:
 */
public class SimpleObservable extends Observable {

    private int data = 0;
    public int getData(){
        return data;
    }

    public void setData(int i){
        if(this.data != i){
            this.data = i;
            setChanged();
            notifyObservers();// 通知观察者，表示状态发生改变。
        }
    }


}
