package com.example.a.myapplication12321321;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.a.myapplication12321321.base.mvp.presenter.BasePresenter;
import com.example.a.myapplication12321321.base.mvp.view.BaseView;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MultipleItem;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MultipleItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultipleRecyclerActivity extends AppCompatActivity implements BaseView<BasePresenter> {
    private static final String TAG = "MultipleRecyclerActivit";
    @BindView(R.id.recycler_multipleview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_recycler);
        ButterKnife.bind(this);

        MultipleItem item = new MultipleItem(MultipleItem.BIG);
        MultipleItem item2 = new MultipleItem(MultipleItem.SMALL);
        List<MultiItemEntity> data = new ArrayList<>();
        data.add(item);
        data.add(item2);
        MultipleItemAdapter adapter = new MultipleItemAdapter(data,getMyContext());
        adapter.setRecyclerManager(recyclerView);

    }


    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
