package com.example.a.myapplication12321321.base.viewpagerfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.example.a.myapplication12321321.BaseFragment;
import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends BaseActivity {

    ViewPager viewPager;

    BottomNavigationView bottomNav;

    private MenuItem menuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void doBusiness(Context mContext) {

    }

    @Override
    protected void setListener() {
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_yw:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_dianxing:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_dianxing1:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.item_dianxing2:
                        viewPager.setCurrentItem(4);
                        break;
                }

                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem != null){
                    menuItem.setChecked(false);
                }else{
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }

                menuItem = bottomNav.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BaseFragment.newInstance("首页"));
        fragments.add(BaseFragment.newInstance("运维"));
        fragments.add(BaseFragment.newInstance("典型"));
        fragments.add(BaseFragment.newInstance("典型"));
        fragments.add(BaseFragment.newInstance("典型"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initView(View mContenxt) {
        bottomNav = mContenxt.findViewById(R.id.bottom_nav);
        viewPager = mContenxt.findViewById(R.id.viewpager);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_view_page;
    }

    @Override
    protected View bindView() {
        return null;
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
