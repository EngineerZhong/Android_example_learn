package com.example.a.myapplication12321321.base.eventbusutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Time:2018/10/29 - 14:44
 * Author:Zhongwb
 * Description:
 * build.gradle   ++   compile 'org.greenrobot:eventbus:3.0.0'
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {
}
