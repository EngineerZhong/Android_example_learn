package com.example.a.myapplication12321321.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a.myapplication12321321.base.mvp.lifecycleobserver.JavaObserver;

/**
 * * Fragment基类
 * 1. 初始化布局 initView
 * 2. 初始化数据 initData
 * Time:2018/12/18 - 17:02
 * Author:Zhongwb
 * Description:
 */


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    //这个activity就是MainActivity
    protected Activity mActivity;
    private final String TAG = getClass().getSimpleName();

    protected FragmentManager fragmentManager = null;

    protected BaseFragment currentFragment = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach()");
    }

    // Fragment被创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new JavaObserver(getClass().getSimpleName()));
        mActivity = getActivity();// 获取所在的activity对象
    }
    // 初始化Fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView()");
        View view = initView();
        return view;
    }
    // activity创建结束
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated()");
        setListener();
        doBussiness();
    }
    /**
     * 设置监听器
     */
    public abstract void setListener();
    /**
     * 初始化布局
     */
    public abstract View initView();

    @Override
    public void onClick(View view) {
        weightOnClick(view);
    }

    public abstract void weightOnClick(View view);

    /**
     * 做一些相关业务上的初始化
     */
    public abstract void doBussiness();

    /**
     * Toast
     * @param msg
     */
    protected void showToastLong(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    /**
     * Toast
     * @param msg
     */
    protected void showToastShort(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        currentFragment = this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach()");
    }

    /**
     * 视图隐藏后，重新展示时会调用该方法。
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(this.getClass().getSimpleName(),"onHiddenChanged: " + hidden);
        currentFragment = this;
    }

    protected void initChildFragmentManager(){
        fragmentManager = this.getChildFragmentManager();
    }
    /**
     * 获取Attach上的FragmentManager
     */
    protected void initParentFragmentManager(){
        fragmentManager = ((BaseActivity)getContext()).getSupportFragmentManager();
    }
    /**
     *
     * @param fragment 添加的Fragment
     * @param id 容器
     * @param tag 标记
     */
    protected void addFragment(BaseFragment fragment, int id, String tag,boolean isBackStack){
        if(fragmentManager != null){
            // 检查Fragment是否存在，避免重叠
            BaseFragment baseFragment = (BaseFragment)fragmentManager.findFragmentByTag(tag);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(baseFragment != null){
                fragment = baseFragment;
            }
            if(fragment.isAdded()){
                addorShowFragment(fragmentTransaction,fragment,id,tag);
            }else{
                if(isBackStack){
                    fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
                }
                if(currentFragment != null && currentFragment.isAdded()){
                    fragmentTransaction.hide(currentFragment).add(id,fragment,tag).commit();
                }else{
                    fragmentTransaction.add(id,fragment,tag).commit();
                }
                currentFragment = fragment;
            }
        }
    }

    protected void addorShowFragment(FragmentTransaction fragmentTransaction, BaseFragment fragment, int id, String tag) {
        if(currentFragment == fragment){
            return;
        }
        if(!fragment.isAdded()){
            fragmentTransaction.hide(currentFragment).add(id,fragment,tag).commit();
        }else{
            fragmentTransaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment.setUserVisibleHint(false);
        currentFragment = fragment;
        currentFragment.setUserVisibleHint(true);
    }


}
