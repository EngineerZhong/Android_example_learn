package com.example.a.myapplication12321321.base.eventbusutils;

import org.greenrobot.eventbus.EventBus;

/**
 * Time:2018/10/29 - 14:49
 * Author:Zhongwb
 * Description:
 */
public class EventBusUtils {

    /**
     * 注册 订阅者
     * @param subscribe
     */
    public static void register(Object subscribe){
        EventBus.getDefault().register(subscribe);
    }

    /**
     * 解绑 订阅者
     * @param subscribe
     */
    public static void unregister(Object subscribe){
        EventBus.getDefault().unregister(subscribe);
    }

    /**
     * 发送事件
     * @param event
     */
    public static void postEvent(Event event){
        EventBus.getDefault().post(event);
    }

    /**
     * 发送 粘性事件
     * 在注册订阅者之前把粘性事件发送出去，注册之后便会受到最近发送的粘性事件（必须匹配）
     * @param event
     */
    public static void postStickyEvent(Event event){
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 取消事件
     * @param event
     */
    public static void  cancelEventDelivery(Event event){EventBus.getDefault().cancelEventDelivery(event);}
}
