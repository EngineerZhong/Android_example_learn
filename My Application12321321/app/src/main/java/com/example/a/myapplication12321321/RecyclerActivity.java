package com.example.a.myapplication12321321;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.a.myapplication12321321.base.mvp.presenter.BasePresenter;
import com.example.a.myapplication12321321.base.mvp.view.BaseView;
import com.example.a.myapplication12321321.base.recyclerviewadapter.JayChou;
import com.example.a.myapplication12321321.base.recyclerviewadapter.JayChouFactory;
import com.example.a.myapplication12321321.base.recyclerviewadapter.SimpleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity implements BaseView<BasePresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
//    第一次只显示几条，PageSize,查询通过(mCurrentCounter,PAGE_NUM);
    private int PAGE_NUM = 5;
//    获得数据的总条数
    private int TOTAL_COUNTER = 20;
//    加载更多的判断方法
    private boolean isErr = false;
//    当前adapter中的数据有多少条
    private int mCurrentCounter = 0;

    private SimpleAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);


        List<JayChou> jayChous = JayChouFactory.createJayChous(0);
          adapter =
                new SimpleAdapter(jayChous,
                        R.layout.rv_meizi_item,
                        RecyclerActivity.this);

/**
 * 头部、尾部位置的View
 */
        View headerView = View.inflate(RecyclerActivity.this,R.layout.rv_header_item,null);
        View footerView = View.inflate(RecyclerActivity.this,R.layout.rv_header_item,null);
        adapter.addHeaderView(headerView);
        adapter.addFooterView(footerView);

        mCurrentCounter = adapter.getData().size();
        adapter.setRecyclerManager(recyclerView);
//        adapter.setGridLayoutManager(recyclerView);

/**
 * 数据为空的情况下展示的页面情况。
 */
        View viewNoData = View.inflate(getMyContext(),R.layout.item_no_data_small_layout,null);
        adapter.setEmptyView(viewNoData);
        viewNoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addData(JayChouFactory.createJayChous(PAGE_NUM));
            }
        });

/**
 * 加载更多的访问情况。
 */
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mCurrentCounter >= TOTAL_COUNTER){
                            adapter.loadMoreEnd();
                        }else{
                            if(isErr){
                                isErr = true;
                                Toast.makeText(getMyContext(),"fail",Toast.LENGTH_SHORT).show();
                                adapter.loadMoreFail();
                            }else {
//                                从服务器获取数据，同时更新mCurrentCounter的条数、重新更新服务器数据总条数。
                                adapter.addData(JayChouFactory.createJayChous(5));
                                mCurrentCounter = adapter.getData().size();
                                adapter.loadMoreComplete();
                            }
                        }
                    }
                },1000);
            }
        },recyclerView);



        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerActivity.this,"HeaderView OnClick",Toast.LENGTH_SHORT).show();
            }
        });

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecyclerActivity.this,"footerView OnClick",Toast.LENGTH_SHORT).show();
            }
        });

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
