package com.example.a.myapplication12321321;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a.myapplication12321321.base.greendao.entity.User;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MainAdapter;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MainDraggAdapter;

import java.util.List;

/**
 * Time:2018/11/13 - 10:24
 * Author:Zhongwb
 * Description:
 */
public class GreenDaoAdapter extends MainDraggAdapter<User> {

    ConstraintLayout layout;
    ImageView imageView;
    TextView tvName;
    TextView tvAge;
    TextView tvSex;

    public GreenDaoAdapter(List<User> data, int itemViewId, Context context) {
        super(data, itemViewId, context);
    }

    @Override
    protected void initView(BaseViewHolder viewHolder, User user) {
        imageView = viewHolder.getView(R.id.imageView);
        tvName = viewHolder.getView(R.id.tv_name);
        tvAge = viewHolder.getView(R.id.tv_age);
        tvSex = viewHolder.getView(R.id.tv_sex);
        layout = viewHolder.getView(R.id.constraint_layout);
    }

    @Override
    protected void initData(BaseViewHolder viewHolder, User user) {
        tvName.setText(user.getName());
        tvAge.setText(user.getAge() + "");
        tvSex.setText(user.getSex());
    }

    @Override
    protected void setListener(BaseViewHolder viewHolder, User user) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
