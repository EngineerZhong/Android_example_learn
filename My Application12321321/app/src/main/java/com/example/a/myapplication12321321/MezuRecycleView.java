package com.example.a.myapplication12321321;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.recyclerviewadapter.JayChouFactory;

public class MezuRecycleView extends BaseActivity {
    private static final String TAG = "MezuRecycleView";
    private static final int PAGE_NUM = 20;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBusiness(Context mContext) {
        adapter = new RecycleViewAdapter(getMyContext(),JayChouFactory.createJayChous(PAGE_NUM));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView(View mContext) {
        recyclerView = mContext.findViewById(R.id.rv_meizu);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_mezu_recycle_view;
    }

    @Override
    protected View bindView() {
        return LayoutInflater.from(getMyContext()).inflate(R.layout.activity_mezu_recycle_view,null);
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
