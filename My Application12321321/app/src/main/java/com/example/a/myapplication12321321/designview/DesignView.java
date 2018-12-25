package com.example.a.myapplication12321321.designview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Time:2018/12/21 - 14:33
 * Author:Zhongwb
 * Description:
 */
public class DesignView extends View {

    private Point controlPoint = new Point(200, 200);
    public DesignView(Context context) {
        super(context);
    }

    public DesignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(ANTI_ALIAS_FLAG);// 参数添加抗锯齿
        paint.setColor(Color.parseColor("#456543"));// 设置颜色
        paint.setStyle(Paint.Style.STROKE);// 设置样式，STROKE 勾边模式、FILL 填充模式、FILL_AND_STROKE 一并使用
        paint.setStrokeWidth(20); // 在勾边模式与一并使用的模式下可设置线条宽度。
        canvas.drawCircle(300,300,200,paint);
        paint.setStyle(Paint.Style.FILL);
        // 画矩形
        RectF rect = new RectF(521,100,600,500);
        canvas.drawRect(rect,paint);

        paint.setStrokeCap(Paint.Cap.ROUND); // 圆形的点。
        canvas.drawPoint(521,521,paint);
        paint.setStrokeCap(Paint.Cap.SQUARE); // 方形的点
        canvas.drawPoint(521,541,paint);
        paint.setStrokeCap(Paint.Cap.BUTT);// 方形的点
        canvas.drawPoint(521,571,paint);
        // 点数组
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50,150,100};
        canvas.drawPoints(points,2,12,paint);// 跳过前两个数、共12个数，绘制六个点。{50,50}{50,100}...

        // 绘制椭圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawOval(new RectF(400, 750, 700, 800), paint);

        // 画线
        canvas.drawLine(200,800,800,1000,paint);
        // 批量画线
        float[] pointss = {100, 900, 900,100,100, 70, 20, 70, 120, 20, 120, 120, 12};
        canvas.drawLines(pointss,paint);

        // 画圆角矩形
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(100,800,500,900),50,50,paint);
        // 画扇形、弧形
        canvas.drawArc(new RectF(100,1000,400,1300),20,-200,true,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(new RectF(100,1000,400,1300),-200,-360,false,paint);
        // DrawPath一般用户绘制组合图形
        Path path = new Path();
        path.addCircle(700,800,300, Path.Direction.CCW);
        canvas.drawPath(path,paint);

        // 填充白色
        canvas.drawColor(Color.parseColor("#FFFFFF"));

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#891265"));


        // 画一条线从0,0 到 600,700 由当前位置 (600, 700) 向正右方 100 像素的位置画一
        Path line = new Path();
        line.lineTo(600,700);
        line.rLineTo(200,0);
        canvas.drawPath(line,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#958542"));
        Path circle = new Path();
        circle.addCircle(200,200,100, Path.Direction.CW);
        canvas.drawPath(circle,paint);

        // 填充白色
        canvas.drawColor(Color.parseColor("#FFFFFF"));


        circle = new Path();
        paint.setStyle(Paint.Style.STROKE);
        circle.moveTo(500,0);
        // 贝塞尔曲线，（600,100）是控制点，（300,300）是终点
        circle.quadTo(600,100,300,300);
        circle.rQuadTo(500,600,400,300);
        canvas.drawPath(circle,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawPoint(600,100,paint);
        canvas.drawPoint(300,300,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        Path beisaier = new Path();
        beisaier.moveTo(100, 500);
        beisaier.quadTo(controlPoint.x, controlPoint.y, 700, 500);
        //绘制路径
        canvas.drawPath(beisaier, paint);
        //绘制辅助点
        canvas.drawPoint(controlPoint.x,controlPoint.y,paint);

        // 贝塞尔曲线，（600,100）是控制点，（300,300）是终点


        // 填充白色
        canvas.drawColor(Color.parseColor("#786E54"));

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#00c1f9"));
        paint.setStrokeWidth(1);
        Path detailLine = new Path();
        detailLine.moveTo(150,250);
        detailLine.lineTo(130,220);
        detailLine.rLineTo(-50,0);
        canvas.drawArc(new RectF(100,200,500,600),-2,-50,true,paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(detailLine,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(26);
        canvas.drawText("YLJ",80,210,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#2D42EA"));
        canvas.drawArc(new RectF(90,190,490,590),180,125,true,paint);
        paint.setColor(Color.parseColor("#b42697"));
        canvas.drawArc(new RectF(100,200,500,600),0,68,true,paint);
        paint.setColor(Color.parseColor("#889630"));
        canvas.drawArc(new RectF(100,200,500,600),70,17,true,paint);
        paint.setColor(Color.parseColor("#f79640"));
        canvas.drawArc(new RectF(100,200,500,600),90,87,true,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                controlPoint.x = (int) event.getX();
                controlPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
