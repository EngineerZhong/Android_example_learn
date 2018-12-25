package com.example.a.myapplication12321321.base.recyclerviewadapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a.myapplication12321321.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Time:2018/11/6 - 16:46
 * Author:Zhongwb
 * Description:
 */
public class SimpleAdapter extends MainAdapter<JayChou> {

    ConstraintLayout layoutMin;
    TextView tvSeat;
    ImageView ivSeat;
    TextView txFavorite;
    ImageView ivFavorite;
    TextView txName;
    ImageView ivPerson;
    TextView tvTitle;
    ImageView imgMain;
    public SimpleAdapter(List<JayChou> data, int itemViewId, Context context) {
        super(data, itemViewId, context);
    }

    @Override
    protected void initView(BaseViewHolder viewHolder, JayChou jayChou) {
        layoutMin = viewHolder.getView(R.id.layout_main);
        tvSeat = viewHolder.getView(R.id.tv_seat);
        ivSeat = viewHolder.getView(R.id.iv_seat);
        txFavorite = viewHolder.getView(R.id.tx_favorite);
        ivFavorite = viewHolder.getView(R.id.iv_favorite);
        txName = viewHolder.getView(R.id.tx_name);
        ivPerson = viewHolder.getView(R.id.iv_person);
        tvTitle = viewHolder.getView(R.id.tv_title);
        imgMain = viewHolder.getView(R.id.img_main);
    }

    @Override
    protected void initData(BaseViewHolder viewHolder, JayChou jayChou) {
//      Glide 图片加载
        Picasso.get()
                .load(jayChou.getImgurl())
                .into(imgMain);
//      设置值
        txFavorite.setText(jayChou.getDate());
        tvSeat.setText(jayChou.getSeat());
    }

    @Override
    protected void setListener(BaseViewHolder viewHolder, JayChou jayChou) {
//        layoutMin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext,"layoutMin OnClick",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}
