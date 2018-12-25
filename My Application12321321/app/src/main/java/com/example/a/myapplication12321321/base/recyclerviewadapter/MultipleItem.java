package com.example.a.myapplication12321321.base.recyclerviewadapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Time:2018/11/7 - 16:21
 * Author:Zhongwb
 * Description:
 */
public class MultipleItem implements MultiItemEntity {

    public static final int BIG = 1;
    public static final int SMALL  = 2;

    private int itemType;

    public MultipleItem(int itemType){
        this.itemType = itemType;
    }


    @Override
    public int getItemType() {
        return itemType;
    }


}
