package com.example.a.myapplication12321321.base.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a.myapplication12321321.R;

import java.util.List;

/**
 * Time:2018/11/7 - 16:12
 * Author:Zhongwb
 * Description:
 */
public class MultipleItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private Context mContext;
    public MultipleItemAdapter(List<MultiItemEntity> data,Context context) {
        super(data);
        addItemType(MultipleItem.BIG, R.layout.rv_jaychou_item_big);
        addItemType(MultipleItem.SMALL, R.layout.rv_meizi_item);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.BIG:

                break;
            case MultipleItem.SMALL:

                break;
        }
    }

    public void setRecyclerManager(RecyclerView recyclerView){
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
    }
}
