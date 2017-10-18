package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/12
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BackLinearlayout extends LinearLayout {
    /**
     * 主体背景色
     */
    private int backColor;
    /**
     * 边框颜色
     */
    private int borderColor;
    /**
     * 边框宽度
     */
    private int borderWidth;
    /**
     * 边框四个圆角
     */
    private float radius;
    /**
     * 边框左上圆角
     */
    private float topLeft;
    /**
     * 边框右上圆角
     */
    private float topRight;
    /**
     * 边框左下圆角
     */
    private float bottomLeft;
    /**
     * 边框右下圆角
     */
    private float bottomRight;
    /**
     * 主体背景画笔
     */
    private Paint paint;
    /**
     * 边框背景画笔
     */
    private Paint paintBorder;

    private Path mPath;

    private float[] radiusf;

    private RectF mReactf = new RectF();

    private String tag="执行顺序";


    private int shapeType;//背景类型

    private float borderDashLength;//虚线线长度
    private float borderDashGapSmall;//虚线点长度
    private float borderDashGap;//虚线间隔


    public BackLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        property(context, attrs);
    }

    public BackLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        property(context, attrs);
    }



    private void property(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.backLinear);
        backColor = ta.getColor(R.styleable.backLinear_backColor, 0x00000000);
        borderColor = ta.getColor(R.styleable.backLinear_borderColor, 0x00000000);
        borderWidth = ta.getDimensionPixelSize(R.styleable.backLinear_borderWidth, 0);
        radius = ta.getDimension(R.styleable.backLinear_radius, 0);
        topLeft = ta.getDimension(R.styleable.backLinear_topLeft, -1);
        topRight = ta.getDimension(R.styleable.backLinear_topRight, -1);
        bottomLeft = ta.getDimension(R.styleable.backLinear_bottomLeft, -1);
        bottomRight = ta.getDimension(R.styleable.backLinear_bottomRight, -1);

        shapeType = ta.getInt(R.styleable.backLinear_shapeType, GradientDrawable.RECTANGLE);

        borderDashLength = ta.getDimension(R.styleable.backLinear_borderDashLength, 5);
        borderDashGapSmall = ta.getDimension(R.styleable.backLinear_borderDashGapSmall, 0);
        borderDashGap = ta.getDimension(R.styleable.backLinear_borderDashGap, 0);
        ta.recycle();
        topLeft = topLeft == -1 ? radius : topLeft;
        topRight = topRight == -1 ? radius : topRight;
        bottomLeft = bottomLeft == -1 ? radius : bottomLeft;
        bottomRight = bottomRight == -1 ? radius : bottomRight;
        borderDashGapSmall = borderDashGapSmall == 0 ? borderDashLength : borderDashGapSmall;
        //边框设置了颜射和宽度才有意义
        if (borderColor != 0&&borderWidth>0) {
            paintBorder=new Paint();
            paintBorder.setColor(borderColor);
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setStrokeWidth(borderWidth);
            paintBorder.setAntiAlias(true);
        }
        //主体画笔设置
        paint=new Paint();
        paint.setColor(backColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        if (radius == 0&& shapeType == GradientDrawable.RECTANGLE) {
            mPath = new Path();
            radiusf = new float[]{topLeft, topLeft, topRight, topRight,
                    bottomRight, bottomRight, bottomLeft, bottomLeft};
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(tag, "onDraw: ");
        if (shapeType == GradientDrawable.RECTANGLE) {//四角形
            if (radius == 0) {
                canvas.drawPath(mPath, paint);
                if (paintBorder != null) {
                    canvas.drawPath(mPath, paintBorder);
                }
            } else {
                canvas.drawRoundRect(mReactf, radius, radius, paint);
                if (paintBorder != null) {
                    if (borderDashGap > 0) {//设置虚线
                        PathEffect effects = new DashPathEffect(new float[]{borderDashLength,
                                borderDashLength, borderDashGapSmall, borderDashLength}, borderDashGap);//设置虚线的间隔和点的长度
                        paintBorder.setPathEffect(effects);
                    }
                    canvas.drawRoundRect(mReactf, radius, radius, paintBorder);
                }
            }

        } else {//椭圆形
            canvas.drawOval(mReactf, paint);
            if (paintBorder != null) {
                if (borderDashGap > 0) {//设置虚线
                    PathEffect effects = new DashPathEffect(new float[]{borderDashLength,
                            borderDashLength, borderDashGapSmall, borderDashLength}, borderDashGap);
                    paintBorder.setPathEffect(effects);
                }
                canvas.drawOval(mReactf, paintBorder);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(tag, "onMeasure: ");
        mReactf.set(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth);
        if (mPath != null) {
            mPath.addRoundRect(mReactf, radiusf, Path.Direction.CW);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(tag, "onLayout: ");
    }

}
