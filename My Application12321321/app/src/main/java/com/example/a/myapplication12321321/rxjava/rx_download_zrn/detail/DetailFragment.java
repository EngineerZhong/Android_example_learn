package com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseFragment;
import com.squareup.picasso.Picasso;

/**
 * Time:2018/12/19 - 16:39
 * Author:Zhongwb
 * Description:
 */
public class DetailFragment extends BaseFragment {

    private ImageView Imgdetail;
    private TextView Txtdetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setListener() {

    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_zrn_detail,null);
        Imgdetail = view.findViewById(R.id.img_zrn_detail);
        Txtdetail = view.findViewById(R.id.txt_zrn_detail);
        return view;
    }

    @Override
    public void weightOnClick(View view) {

    }

    @Override
    public void doBussiness() {
        Picasso.get().load(getArguments().get("url").toString()).into(Imgdetail);
        Txtdetail.setText(getArguments().get("detail").toString());
    }
}
