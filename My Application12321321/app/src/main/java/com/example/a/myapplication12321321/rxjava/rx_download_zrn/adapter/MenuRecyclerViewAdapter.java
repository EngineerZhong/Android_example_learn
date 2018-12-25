package com.example.a.myapplication12321321.rxjava.rx_download_zrn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.recyclerviewadapter.MainAdapter;
import com.example.a.myapplication12321321.base.utils.ScreenUtil;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail.MenuEntity;

import java.util.List;

/**
 * Time:2018/12/20 - 14:35
 * Author:Zhongwb
 * Description: 10宫格菜单项适配器
 */
public class MenuRecyclerViewAdapter extends MainAdapter<MenuEntity> {
    private static final String TAG = "MenuRecyclerViewAdapter";
    //  标识页面
    private int mIndex;
    //  单页显示数量
    private int mPageSize;
    //  菜单数据项
    private List<MenuEntity> data;
    //  布局元素变量
    private ImageView itemImg;
    private TextView itemText;
    private FrameLayout itemView;

    public MenuRecyclerViewAdapter(List<MenuEntity> data, int itemViewId, Context context, int index, int pageSize) {
        super(data, itemViewId, context);
        this.mIndex = index;
        this.mPageSize = pageSize;
        this.data = data;
    }

    @Override
    protected void initView(BaseViewHolder viewHolder, MenuEntity menuEntity) {
//      初始化布局元素
        itemImg = viewHolder.getView(R.id.img_menuitem_rc);
        itemText = viewHolder.getView(R.id.txt_menuitem_rc);
        itemView = viewHolder.getView(R.id.ll_frame);
//      子控件告诉父控件 自己要如何布局。
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 4.0f));
        itemView.setLayoutParams(layoutParams);
    }

    @Override
    protected void initData(BaseViewHolder viewHolder, MenuEntity menuEntity) {
//      对第二页的数据进行定位。第一页头0+0*10=0 下一页头：0+1*10=10
        int position = getPosition(viewHolder) + mIndex * mPageSize;
        itemImg.setImageResource(data.get(position).getImg());
        itemText.setText(data.get(position).getName());

    }

    @Override
    protected void setListener(BaseViewHolder viewHolder, final MenuEntity menuEntity) {
        final int position = getPosition(viewHolder) + mIndex * mPageSize;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size() > (mIndex + 1) * mPageSize ? mPageSize : (data.size() - mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }
}
