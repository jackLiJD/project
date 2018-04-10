package com.example.lijinduo.mydemo.lineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/26
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class LinearGradientTextView extends android.support.v7.widget.AppCompatTextView{
    LinearGradient mLinearGradient;
    private int mViewWidth=0;
    private int mViewHeight=0;
    //画笔
    private Paint mPaint;

    public LinearGradientTextView(Context context) {
        this(context,null);
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=getPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mLinearGradient = new LinearGradient(0,  0,mViewWidth, mViewHeight,
                new int[] {Color.BLUE, Color.RED },
                new float[] { 0, 1f }, Shader.TileMode.CLAMP); //边缘融合
        mPaint.setShader(mLinearGradient);//设置渐变
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth=getMeasuredWidth();
        mViewHeight=getMeasuredHeight();
    }
}
