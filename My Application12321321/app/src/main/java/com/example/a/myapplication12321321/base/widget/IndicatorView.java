package com.example.a.myapplication12321321.base.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.eventbusutils.Event;
import com.example.a.myapplication12321321.base.eventbusutils.EventBusUtils;
import com.example.a.myapplication12321321.base.utils.ScreenUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Time:2018/12/20 - 11:22
 * Author:Zhongwb 指示器，转载 @xiaohaibin
 * Description: 布局、绘制、触摸反馈
 */
public class IndicatorView extends View {
    private int indicatorColor = Color.rgb(0, 0, 0);
    private int indicatorColorSelected = Color.rgb(0, 0, 0);
    private int indicatorWidth = 0;
    private int gravity = 0;

    private int indicatorCount = 0;
    private int currentIndicator = 0;

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x12) {
//                invalidate();
//            }
//        }
//    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void invalidateView(Event<String> event){
        if("invalidate".equals(event.getData())){
            Log.i("Thread",Thread.currentThread() + "");
            invalidate();
        }
    }

    public IndicatorView(Context context) {
        super(context);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IndicatorView);
            indicatorColor = typedArray.getColor(R.styleable.IndicatorView_indicatorColor, Color.rgb(0, 0, 0));
            indicatorColorSelected = typedArray.getColor(R.styleable.IndicatorView_indicatorColorSelected, Color.rgb(0, 0, 0));
            indicatorWidth = ScreenUtil.dip2px(typedArray.getInt(R.styleable.IndicatorView_indicatorWidth, 0));
            gravity = typedArray.getInt(R.styleable.IndicatorView_gravity, 0);
            typedArray.recycle();
        }
        EventBusUtils.register(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int totalWidth = indicatorWidth * (2 * indicatorCount - 1);
        RectF rectF;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (indicatorCount > 0) {
            for (int i = 0; i < indicatorCount; i++) {
                if (i == currentIndicator) {
                    paint.setColor(indicatorColorSelected);
                } else {
                    paint.setColor(indicatorColor);
                }
                int left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                switch (gravity) {
                    case 0:
                        left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                        break;
                    case 1:
                        left = i * 2 * indicatorWidth;
                        break;
                    case 2:
                        left = viewWidth - totalWidth + (i * 2 * indicatorWidth);
                        break;

                }
                int top = (viewHeight - indicatorWidth) / 2;
                int right = left + indicatorWidth;
                int bottom = top + indicatorWidth;
                rectF = new RectF(left, top, right, bottom);
                canvas.drawOval(rectF, paint);
            }
        }
    }

    public void setIndicatorCount(int indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    public void setCurrentIndicator(int currentIndicator) {
        this.currentIndicator = currentIndicator;
        EventBusUtils.postEvent(new Event(0,"invalidate"));
    }

}
