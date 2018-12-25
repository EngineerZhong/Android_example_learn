package com.example.a.myapplication12321321.base.okhttputils;

/**
 * Time:2018/10/30 - 18:12
 * Author:Zhongwb
 * Description:
 */
public class Speed {

    private Integer progress;
    private String speed;

    public Speed(Integer progress, String speed) {
        this.progress = progress;
        this.speed = speed;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
