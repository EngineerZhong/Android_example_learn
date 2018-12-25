package com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.data;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MainAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Time:2018/12/19 - 11:33
 * Author:Zhongwb
 * Description:
 */
public class PubuZrnAdapter extends MainAdapter<Zrn> {

    private ImageView img_head;
    private TextView txt_detail;

    public PubuZrnAdapter(List<Zrn> data, int itemViewId, Context context) {
        super(data, itemViewId, context);
    }

    @Override
    protected void initView(BaseViewHolder viewHolder, Zrn zrn) {
        img_head = viewHolder.getView(R.id.img_pubu_zrn);
        txt_detail = viewHolder.getView(R.id.txt_pubu_zrn);
    }

    @Override
    protected void initData(BaseViewHolder viewHolder, Zrn zrn) {
        Picasso.get()
                .load(zrn.getUrl())
                .into(img_head);
        txt_detail.setText(zrn.getDetial());
    }

    @Override
    protected void setListener(BaseViewHolder viewHolder, Zrn zrn) {

    }
}
