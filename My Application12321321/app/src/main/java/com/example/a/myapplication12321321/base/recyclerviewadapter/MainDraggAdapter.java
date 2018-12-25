package com.example.a.myapplication12321321.base.recyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import java.util.List;

/**
 * recyclerview适配器。
 */
public abstract class MainDraggAdapter<T> extends BaseItemDraggableAdapter<T,BaseViewHolder> {

//    上下文
    protected Context mContext;
//    数据源
    protected List<T> mData;
//    ItemView Layout
    protected int mItemViewId;
//    HeadView
    protected View mHeaderView;
//    FooterView
    protected View mFooterView;

    public MainDraggAdapter(List<T> data, int itemViewId, Context context){
        super(itemViewId,data);
        this.mData = data;
        this.mItemViewId = itemViewId;
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, T item) {
        initView(viewHolder,item);
        initData(viewHolder,item);
        setListener(viewHolder,item);
    }

    protected abstract void initView(BaseViewHolder viewHolder, T t);
    protected abstract void initData(BaseViewHolder viewHolder, T t);
    protected abstract void setListener(BaseViewHolder viewHolder, T t);

    /**获取position，当添加有header或footer要注意改变**/
    public int getPosition(BaseViewHolder viewHolder){
        return viewHolder.getLayoutPosition();
    }

    /**获取headerView**/
    protected View getHeaderView(int headerViewId) {
        if(mContext!=null){
            mHeaderView=LayoutInflater.from(mContext).inflate(headerViewId, null);
        }
        return mHeaderView;
    }

    /**获取footerView**/
    protected View getFooterView(int footerViewId) {
        if (mContext != null&&mFooterView==null) {
            mFooterView = LayoutInflater.from(mContext).inflate(footerViewId, null);
        }
        return mFooterView;
    }

    /**添加headerView**/
    public void addHeaderView(int headerViewId){
        addHeaderView(getHeaderView(headerViewId));
    };

    /**添加footerView**/
    public void addFooterView(int footerViewId){
        addFooterView(getFooterView(footerViewId));
    }

    /**设置RecyclerView**/
    public void setRecyclerManager(RecyclerView recyclerView){
        LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
        openLoadAnimation();//默认adapter渐现效果
    }

    public void setDraggSwipe(RecyclerView recyclerView){
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        this.enableSwipeItem();
    }


    /**adapter渐现动画**/
    public void openAlphaAnimation(){
        openLoadAnimation(BaseQuickAdapter.ALPHAIN);
    }

    /**adapter缩放动画**/
    public void openScaleAnimation(){
        openLoadAnimation(BaseQuickAdapter.SCALEIN );
    }

    /**adapter从下到上动画**/
    public void openBottomAnimation(){
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM  );
    }

    /**adapter从左到右动画**/
    public void openLeftAnimation(){
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT   );
    }

    /**adapter从右到左动画**/
    public void openRightAnimation(){
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT    );
    }

    /**自定义动画**/
    public void openLoadAnimation(BaseAnimation animation){

    }

}