package com.example.a.myapplication12321321.base.mvp;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.a.myapplication12321321.base.eventbusutils.BindEventBus;
import com.example.a.myapplication12321321.base.eventbusutils.EventBusUtils;
import com.example.a.myapplication12321321.base.mvp.lifecycleobserver.JavaObserver;
import com.example.a.myapplication12321321.base.mvp.view.IView;
import com.example.a.myapplication12321321.base.permissiongrantutils.PermissionUtils;

import butterknife.ButterKnife;


/**
 * Time:2018/10/29 - 14:42
 * Author:Zhongwb
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private boolean isSetStatusBar = false;

    private boolean isAllowScreenRoate = false;

    private boolean isAllowFullScreen = false;

    private View mContenxt = null;

    protected static final String TAG = "BaseActivity";

    protected FragmentManager fragmentManager = null;

    protected FragmentTransaction fragmentTransaction = null;

    protected BaseFragment currentFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkEventBusBind();
        ActivityCollector.addActivity(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            initParams(bundle);
        }
        View mView = bindView();
        if(null == mView){
            mContenxt = LayoutInflater.from(this).inflate(bindLayout(),null);
        }else{
            mContenxt = mView;
        }

        ButterKnife.bind(mContenxt);
        if(!isAllowScreenRoate){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        if (isSetStatusBar) {
            steepStaturBar();
        }

        if(isAllowFullScreen){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        getLifecycle().addObserver(new JavaObserver(TAG));
        setContentView(mContenxt);
        initView(mContenxt);
        setListener();
        doBusiness(this);
    }

    /**
     * 添加碎片Fragment相关
     */
    protected void initFragmentManager(){
        fragmentManager = ((BaseActivity)getMyContext()).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
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

    /**
     * 权限请求
     * @param permissionGrant
     * @param requestCode
     */
    protected  void requestPermission(PermissionUtils.PermissionGrant permissionGrant,int[] requestCode){
        PermissionUtils.requestMultiPermissions(this,permissionGrant,requestCode);
    }
    /**
     * 业务操作
     * @param mContext
     */
    protected abstract void doBusiness(Context mContext);

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        widgetClick(view);
    }

    /**
     * 初始化控件
     * @param mContext
     */
    protected abstract void initView(View mContext);

    /**
     * 绑定控件
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T $(int resId){
        return (T)super.findViewById(resId);
    }

    /**
     * 未携带数据的页面跳转
     * @param clz
     */
    public void startActivity(Class<?> clz){
        startActivity(new Intent(BaseActivity.this,clz));
    }
    /**
     * 携带Bundle数据的页面跳转
     * @param cls
     * @param bundle
     */
    public void startActivity(Class<?> cls,Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        if(bundle != null){
            intent.putExtras(bundle);
        }

        startActivity(intent);
    }
    /**
     * 含有Bundle通过Class打开页面，并请求返回相关数据内容。
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls,Bundle bundle,int requestCode){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }
    /**
     * 沉浸式状态栏
     */
    private void steepStaturBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * Toast
     * @param msg
     */
    protected void showToastLong(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    /**
     * Toast
     * @param msg
     */
    protected void showToastShort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    /**
     * 设置屏幕是否可以旋转
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate){
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    public void setSetStatusBar(boolean setStatusBar) {
        this.isSetStatusBar = setStatusBar;
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.isAllowFullScreen = allowFullScreen;
    }

    protected abstract int bindLayout();

    protected abstract View bindView();


    protected abstract void initParams(Bundle bundle);
    
    public abstract void widgetClick(View view);

    /**
     * 通过类是否使用@BindEventBus注解
     * 检查是否需要绑定EventBus
     */
    private void checkEventBusBind() {
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.register(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 通过类是否使用@BindEventBus注解
     * 检查是否解绑EventBus
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.getClass().isAnnotationPresent(BindEventBus.class)){
            EventBusUtils.unregister(this);
        }

        ActivityCollector.removeActivity(this);
    }


}
