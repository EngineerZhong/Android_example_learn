package com.example.a.myapplication12321321.base.pictureselector;

import android.os.Environment;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

/**
 * Time:2018/11/1 - 11:36
 * Author:Zhongwb
 * Description:
 */
public class PictureSelectorUtil {



    public static String getPath(String directoryName) {
        String path = Environment.getExternalStorageDirectory().getPath() + "/" + directoryName;
        File pathFile = new File(path);
        if (pathFile.exists() && pathFile.isDirectory()) {
            return path;
        } else {
            pathFile.mkdirs();
            return path;
        }
    }

    public static File[] getFiles(List<LocalMedia> medias,boolean flag){
        File[] result = new File[medias.size()];

        for (int i = 0; i < medias.size(); i++) {
            String path = flag?medias.get(i).getCompressPath():medias.get(i).getPath();
            result[i] = new File(path);
        }
        return result;
    }

    public static String[] getFilesKey(List<LocalMedia> medias){
        String[] fileKeys = new String[medias.size()];
        for(int i =0;i<medias.size();i++){
            fileKeys[i] = "img";
        }
        return fileKeys;
    }

}
