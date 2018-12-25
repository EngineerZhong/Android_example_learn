package com.example.a.myapplication12321321;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.example.a.myapplication12321321.base.eventbusutils.BindEventBus;
import com.example.a.myapplication12321321.base.eventbusutils.Event;
import com.example.a.myapplication12321321.base.eventbusutils.EventBusUtils;
import com.example.a.myapplication12321321.base.greendao.database.DaoSession;
import com.example.a.myapplication12321321.base.greendao.database.UserDao;
import com.example.a.myapplication12321321.base.greendao.entity.User;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;
import com.example.a.myapplication12321321.base.mvp.MyApplication;
import com.example.a.myapplication12321321.base.recyclerviewadapter.SimpleAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


@BindEventBus
public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.btn_insertdata)
    Button btnInsertdata;
    @BindView(R.id.btn_deletedata)
    Button btnDeletedata;
    @BindView(R.id.btn_updatedata)
    Button btnUpdatedata;
    @BindView(R.id.btn_querydata)
    Button btnQuerydata;
    @BindView(R.id.btn_querythroughcondition)
    Button btnQuerythroughcondition;
    @BindView(R.id.data_recyclerview)
    RecyclerView dataRecyclerview;

    private DaoSession daoSession;
    private GreenDaoAdapter adapter;
    private List<User> users = new ArrayList<>();
    private final String[] names = {"YLJ", "ZWB", "ADF", "DSF", "FQW", "QWE", "FDE", "VDZ", "GRF", "QWR"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insertdata:
                insertData();
                break;
            case R.id.btn_deletedata:
//                deleteData();
                deleteDataByQueryBuilder();
                break;
            case R.id.btn_updatedata:

                break;
            case R.id.btn_querydata:
                queryData();
                break;
            case R.id.btn_querythroughcondition:
                queryThroughCondition();
                break;
        }
    }

    private void queryThroughCondition() {
        List<User> users = daoSession.queryBuilder(User.class)
                .where(UserDao.Properties.Sex.eq("male")
                        ,UserDao.Properties.Age.gt(11)).build().list();
        refreshData(users);
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter(){
        adapter = new GreenDaoAdapter(users, R.layout.greendao_item, getMyContext());
        adapter.setDraggSwipe(dataRecyclerview);
        adapter.setRecyclerManager(dataRecyclerview);
        adapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                daoSession.delete(adapter.getData().get(pos));
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
    }

    private void deleteDataByQueryBuilder(){
        QueryBuilder<User> where = daoSession.queryBuilder(User.class);
//                .where(UserDao.Properties.Sex.eq("male"));
        DeleteQuery<User> deleteQuery = where.buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }


    /**
     * 删除数据
     */
    private void deleteData() {
        try {
            List<User> userList = daoSession
                    .queryBuilder(User.class)
                    .where(UserDao.Properties.Age.ge(11))
                    .build()
                    .list();
            if (userList != null && userList.size() > 0) {
                for (User user : userList) {
                    daoSession.delete(user);
                }
                EventBusUtils.postEvent(new Event(1, "删除成功"));
                queryData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            EventBusUtils.postEvent(new Event(1, "删除失败"));
        }

//        daoSession.deleteAll(User.class);
    }

    private void queryData() {
        List<User> userList = daoSession.loadAll(User.class);
        refreshData(userList);
    }

    private void refreshData(List<User> userList) {
        adapter.getData().clear();
        adapter.setNewData(userList);
    }


    /**
     * 插入数据。
     */
    private void insertData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 50; i++) {
                    User user = new User();
                    user.setName(names[random.nextInt(9)]);
                    user.setSex(i % 2 == 0 ? "male" : "female");
                    user.setAge(random.nextInt(100) + 1);
                    user.setTelPhone(random.nextInt(100000000) + "");
                    user.setStudentNo(random.nextInt(1000000));
                    daoSession.insert(user);
                }
                random = null;
                EventBusUtils.postEvent(new Event(0, "插入成功"));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(Event<String> msg) {
        switch (msg.getCode()) {
            case 0:
                showToastShort(msg.getData());
                break;
            case 1:
                showToastShort(msg.getData());
                break;
        }
    }

    @Override
    protected void doBusiness(Context mContext) {
        daoSession = MyApplication.getApplication(this).getDaoSession();
    }

    @Override
    protected void setListener() {
        btnInsertdata.setOnClickListener(this);
        btnDeletedata.setOnClickListener(this);
        btnUpdatedata.setOnClickListener(this);
        btnQuerydata.setOnClickListener(this);
        btnQuerythroughcondition.setOnClickListener(this);
    }

    @Override
    protected void initView(View mContenxt) {
        ButterKnife.bind(this);
        initAdapter();
        View viewNoData = View.inflate(getMyContext(),R.layout.item_no_data_small_layout,null);
        adapter.setEmptyView(viewNoData);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_green_dao;
    }

    @Override
    protected View bindView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_green_dao, null);
        return view;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }


    @Override
    public Context getMyContext() {
        return this;
    }
}
