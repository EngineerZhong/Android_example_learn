package com.example.a.myapplication12321321.rxjava.rx_download_zrn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseFragment;
import com.example.a.myapplication12321321.base.mvp.lifecycleobserver.JavaObserver;
import com.example.a.myapplication12321321.base.utils.ScreenUtil;
import com.example.a.myapplication12321321.base.widget.IndicatorView;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.adapter.MenuRecyclerViewAdapter;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.adapter.MenuViewPagerAdapter;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail.DetailFragment;
import com.example.a.myapplication12321321.rxjava.rx_download_zrn.detail.MenuEntity;
import com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.PubuFragment;
import com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.data.Zrn;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Time:2018/12/18 - 16:59
 * Author:Zhongwb
 * Description:
 */
public class RxFragment extends BaseFragment implements RxContract.RxView {
    private static final String TAG = "RxFragment";
    // begin: 宫格菜单的变量
    public static final int HOME_PAGE = 10;
    private ViewPager menuViewPager;
    private LinearLayout menuLayout;
    private List<MenuEntity> entities;
    private IndicatorView indicatorView;
    // end
    private RxContract.RxPresenter mRxPresenter;
    private Button  btn,btn_pbl,btnTest;
    private ImageView img;
    private Fragment frameLayout;
    private Context mContext;
    private XBanner xbanner;
    private List<View> viewList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPresenter(new RxModel(),this);
        getLifecycle().addObserver(new JavaObserver(TAG));
//      初始化FragmentManager
        initParentFragmentManager();
    }

    @Override
    public void setListener() {
        btn.setOnClickListener(this);
        img.setOnClickListener(this);
        btn_pbl.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_rx,null);
        btn = view.findViewById(R.id.btn_rx);
        img = view.findViewById(R.id.rx_img);
        btn_pbl = view.findViewById(R.id.btn_pbl);
        btnTest = view.findViewById(R.id.btn_test);
        xbanner = view.findViewById(R.id.banner);
        // begin 初始化
        menuViewPager = view.findViewById(R.id.vp_menu);
        menuLayout = view.findViewById(R.id.ll_menu);
        indicatorView = view.findViewById(R.id.view_indicator);
        return view;
    }

    @Override
    public void weightOnClick(View view) {
        switch (view.getId()){
            case R.id.btn_rx:
                mRxPresenter.downloadFromBytes("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=42" +
                        "57516017,2210422111&fm=173&app=25&f=JPEG?w=640&h=920&s=190B995F742211192801" +
                        "10A90300A000");
                break;
            case R.id.rx_img:
                Bundle bundle = new Bundle();
                bundle.putString("detail","章若楠，1996年11月出生于浙江省温州市，中国内地女演员.");
                bundle.putString("url","https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=42" +
                "57516017,2210422111&fm=173&app=25&f=JPEG?w=640&h=920&s=190B995F742211192801" +
                        "10A90300A000");
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);
                addFragment(detailFragment,R.id.ll_frame,"DetailFragment",true);
                break;
            case R.id.btn_pbl:
                addFragment(new PubuFragment(),R.id.ll_frame,"PubuFragment",true);
                break;
            case R.id.btn_test:


                break;
        }
    }


    @SuppressLint("CheckResult")
    @Override
    public void doBussiness() {
        /**
         * XBanner 广告框架的运用。
         */
        List<Zrn> data = new ArrayList<>();
        String[] urls = new String[]{
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660314&di=c0811afa961b846567cbde6a31ef18f6&imgtype=0&src=http%3A%2F%2Fmp1.iqiyipic.com%2Fimage%2F20181119%2Fc9%2F91%2Fppu_574934560102_pp_601_300_300.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660589&di=29b70889b6604eac445b776aefbde531&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F69%2Fw500h369%2F20181210%2FxX4P-hpinryc9715343.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660585&di=5131dd8bc66358017aadecc2b4c680a5&imgtype=0&src=http%3A%2F%2Fp1.hoopchina.com.cn%2Fgdc%2Fweibo%2Fpic%2F24dccf0fc7835ddfbf73b8e410ad09be.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660584&di=d849e1a87c33791be833bbf0cfd6d39c&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F6543127340%2F1000",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660584&di=2255235dd8fa0258159692ef28133981&imgtype=0&src=http%3A%2F%2Fpic.rmb.bdstatic.com%2F4ea8ff7dfb7dfbbbafa19f2ad295b74b.jpeg%40wm_2%2Ct_55m%2B5a625Y%2B3L%2BW9seinhua1qeWTpQ%3D%3D%2Cfc_ffffff%2Cff_U2ltSGVp%2Csz_23%2Cx_15%2Cy_15"
        };
        List<String> tips = new ArrayList<>();
        for(String string : urls){
            data.add(new Zrn(string,"章若楠"));
            tips.add("章若楠");
        }
        xbanner.setData(data,tips);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Picasso.get().load(((Zrn)model).getUrl()).into((ImageView) view);
            }
        });
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                showToastShort(((Zrn)model).getDetial());
            }
        });
        mRxPresenter.initMenuEntity();
    }

    @Override
    public void setPresenter(RxContract.RxPresenter presenter) {
        if (presenter == null) {
            throw new NullPointerException();
        }
        mRxPresenter = presenter;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    public void setImage(byte[] bytes) {
        img.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
    }

    /**
     * 设置菜单项
     * @param menuEntities
     */
    @Override
    public void setData(List<MenuEntity> menuEntities) {
        this.entities = menuEntities;
    }

    @Override
    public void initMenuLayout() {
        ScreenUtil.init(mContext);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        (int)((float)ScreenUtil.getScreenWidth()/2.0));
        LinearLayout.LayoutParams menuLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                ,(int) ((float)ScreenUtil.getScreenWidth()/2.0 + 75));
//
        menuLayout.setLayoutParams(menuLayoutParams);
        menuViewPager.setLayoutParams(layoutParams);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        // 将RecyclerView放入到ViewPager中
        int pageSize = HOME_PAGE;
        // 一共的页数等于 总数/每页数量，取整
        int pageCount = (int) Math.ceil(entities.size() * 1.0 / pageSize);
        viewList = new ArrayList<>();
        for(int index = 0;index < pageCount;index ++) {
            RecyclerView recyclerView = (RecyclerView) inflater
                    .inflate(R.layout.item_recycleview_vp, menuViewPager, false);
            recyclerView.setLayoutParams(layoutParams);
//            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
//            EntranceAdapter entranceAdapter = new EntranceAdapter(getContext(), entities, index, HOME_PAGE);
//            recyclerView.setAdapter(entranceAdapter);
            MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(entities,R.layout.item_menu_rc,mContext,index,HOME_PAGE);
            adapter.setGridLayoutManager(recyclerView);
            viewList.add(recyclerView);
        }
        MenuViewPagerAdapter adapter = new MenuViewPagerAdapter(viewList);
        menuViewPager.setAdapter(adapter);
        indicatorView.setIndicatorCount(menuViewPager.getAdapter().getCount());
        indicatorView.setCurrentIndicator(menuViewPager.getCurrentItem());
        Log.i(TAG,Thread.currentThread() + "");
        menuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.setCurrentIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
