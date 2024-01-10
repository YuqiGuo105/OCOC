package com.example.club.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.club.R;

public class Indicator extends View {
    //前景色的圆的画笔
    private Paint mForePaint;
    //背景颜色的画笔
    private Paint mBgPaint;
    //小圆点数量
    private int mNumber =3;
    //小圆点半径
    private int mRadius = 10;
    //小圆点的默认背景色画笔颜色
    private int mBgColor = Color.YELLOW;
    //小圆点的默认前景色画笔颜色
    private int mForeColor=Color.BLACK;
    /***
     * 设置偏移量的方法
     * @param position
     * @param positionOffset
     */
    public void setOffset(int position,float positionOffset) {
        position %= mNumber;
        mOffset = position * 3 * mRadius + positionOffset * 3 * mRadius;
        //重绘
        invalidate();
    }

    /**移动的偏移量**/
    private float mOffset;

    //代码里面用到的
    public Indicator(Context context) {
        super(context);
    }

    //xml布局里面用到的
    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获得布局文件中得到的小圆点的数量，半径，两种状态下的颜色颜色
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        mNumber = typedArray.getInteger(R.styleable.Indicator_setNumber, mNumber);
        mRadius=typedArray.getInteger(R.styleable.Indicator_Indicator_radius, mRadius);
        mBgColor=typedArray.getColor(R.styleable.Indicator_Indicator_bgColor, mBgColor);
        mForeColor=typedArray.getColor(R.styleable.Indicator_Indicator_foreColor, mForeColor);
        initPaint();

    }

    private void initPaint() {
        //画填充圆
        mForePaint = new Paint();
        //设置抗锯齿
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(mForeColor);
        mForePaint.setStrokeWidth(2);
        //画圆圈
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStrokeWidth(2);

    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置绘制的圆点居中
        int w=getWidth()/2- mNumber * mRadius * 3/2;
        for (int i = 0; i < mNumber; i++) {
            canvas.drawCircle(w + i * mRadius * 3,60,mRadius,mBgPaint);
        }
        canvas.drawCircle(w + mOffset,60,mRadius,mForePaint);
    }
}
