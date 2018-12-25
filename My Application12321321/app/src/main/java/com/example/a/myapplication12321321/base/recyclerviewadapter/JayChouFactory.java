package com.example.a.myapplication12321321.base.recyclerviewadapter;

import java.util.ArrayList;
import java.util.List;

public class JayChouFactory {


    private static String[] imgsUrl = {
            "https://upload-images.jianshu.io/upload_images/11181600-db48df38b76ed467.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-f9cc67a96b58d49e.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-4860ecb6acb8fce5.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-a5db9d1018924867.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-d2131192b602b425.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-e3002c837b5335f9.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-2ddfaaa766a399e5.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-d58b814b598ea3d3.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-8e2e151505cf6899.jpg",
            "https://upload-images.jianshu.io/upload_images/11181600-a535283f3402accb.jpg"
    };

    private static String[] dates = {
            "2018-10-27",
            "2018-10-28",
            "2018-10-28",
            "2018-10-27",
            "2018-10-28",
            "2018-10-28",
            "2018-10-27",
            "2018-10-28",
            "2018-10-28",
            "2018-10-27",
    };

    private static String[] seats = {
      "125区8排8号",
            "125区8排9号",
            "125区8排10号",
            "125区8排11号",
            "125区8排12号",
            "125区8排13号",
            "125区8排14号",
            "125区8排15号",
            "125区8排16号",
            "125区8排17号",
    };


    public static List<JayChou> createJayChous(int num){

        List<JayChou> jayChous = new ArrayList<>();
        int anyOne = imgsUrl.length;
        for(int i = 0;i<num;i++){
            int a = i%anyOne;
            String url = imgsUrl[a];
            String date = dates[a];
            String seat = seats[a];
            jayChous.add(new JayChou(date,seat,url));
        }

        return jayChous;
    }

}
