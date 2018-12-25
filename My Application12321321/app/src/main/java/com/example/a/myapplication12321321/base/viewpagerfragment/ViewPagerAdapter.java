package com.example.a.myapplication12321321.base.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Time:2018/11/8 - 16:24
 * Author:Zhongwb
 * Description:
 */
class ViewPagerAdapter extends FragmentPagerAdapter {

    FragmentManager supportFragmentManager;
    List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.supportFragmentManager = fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
