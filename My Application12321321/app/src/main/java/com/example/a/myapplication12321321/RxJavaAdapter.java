package com.example.a.myapplication12321321;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MainAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Time:2018/11/14 - 11:12
 * Author:Zhongwb
 * Description:
 */
public class RxJavaAdapter extends MainAdapter<Bitmap> {


    ImageView imgRvItem;

    public RxJavaAdapter(List<Bitmap> data, int itemViewId, Context context) {
        super(data, itemViewId, context);
    }

    @Override
    protected void initView(BaseViewHolder viewHolder, Bitmap bitmap) {
        imgRvItem = viewHolder.getView(R.id.img_rv_item);
    }

    @Override
    protected void initData(BaseViewHolder viewHolder, Bitmap bitmap) {
        imgRvItem.setImageBitmap(bitmap);
    }

    @Override
    protected void setListener(BaseViewHolder viewHolder, Bitmap bitmap) {

    }
}
