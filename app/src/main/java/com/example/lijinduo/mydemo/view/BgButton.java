//package com.example.lijinduo.mydemo.view;
//
///**
// * 版权：XXX公司 版权所有
// * 作者：lijinduo
// * 版本：2.0
// * 创建日期：2017/10/12
// * 描述：(重构)
// * 修订历史：
// * 参考链接：
// */
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.DashPathEffect;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.PathEffect;
//import android.graphics.RectF;
//import android.graphics.drawable.GradientDrawable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.lijinduo.mydemo.R;
//
//
//public class BgButton extends LinearLayout {
//
//    private int bgColor;//背景颜色
//    private int borderColor;//边框颜色
//    private int borderWidth;
//
//    private float radius;//圆角
//    private float topLeftRadius;
//    private float topRightRadius;
//    private float bottomLeftRadius;
//    private float bottomRightRadius;
//
//    private int shapeType;//背景类型
//
//    private float borderDashLength;//虚线线长度
//    private float borderDashGapSmall;//虚线点长度
//    private float borderDashGap;//虚线间隔
//
//    private Paint paintBg;
//    private Paint paintBorder;
//
//    private Path mPath;
//    private RectF mReactf = new RectF();
//    private float[] radiusf;
//    private String tag="执行顺序";
//
//    public BgButton(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, attrs);
//    }
//
//    public BgButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context, attrs);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }
//
//    private void init(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BgButton);
//
//        bgColor = typedArray.getColor(R.styleable.BgButton_bgColor, 0x00000000);
//        borderColor = typedArray.getColor(R.styleable.BgButton_cornerRadiusColor, 0x00000000);
//        borderWidth = typedArray.getDimensionPixelSize(R.styleable.BgButton_borderWidth, 1);
//
//        radius = typedArray.getDimension(R.styleable.BgButton_cornerRadius, 0);
//        topLeftRadius = typedArray.getDimension(R.styleable.BgButton_topLeftRadius, -1);
//        topRightRadius = typedArray.getDimension(R.styleable.BgButton_topRightRadius, -1);
//        bottomLeftRadius = typedArray.getDimension(R.styleable.BgButton_bottomLeftRadius, -1);
//        bottomRightRadius = typedArray.getDimension(R.styleable.BgButton_bottomRightRadius, -1);
//        shapeType = typedArray.getInt(R.styleable.BgButton_shapeType, GradientDrawable.RECTANGLE);
//
//        borderDashLength = typedArray.getDimension(R.styleable.BgButton_borderDashLength, 5);
//        borderDashGapSmall = typedArray.getDimension(R.styleable.BgButton_borderDashGapSmall, 0);
//        borderDashGap = typedArray.getDimension(R.styleable.BgButton_borderDashGap, 0);
//        typedArray.recycle();
//        initDraw();
//    }
//
//
//    private void initDraw() {
//        topLeftRadius = topLeftRadius == -1 ? radius : topLeftRadius;
//        topRightRadius = topRightRadius == -1 ? radius : topRightRadius;
//        bottomLeftRadius = bottomLeftRadius == -1 ? radius : bottomLeftRadius;
//        bottomRightRadius = bottomRightRadius == -1 ? radius : bottomRightRadius;
//        borderDashGapSmall = borderDashGapSmall == 0 ? borderDashLength : borderDashGapSmall;
//
//        if (borderWidth > 0 && borderColor != 0) {
//            paintBorder = new Paint();
//            paintBorder.setColor(borderColor);
//            paintBorder.setStyle(Paint.Style.STROKE);
//            paintBorder.setStrokeWidth(borderWidth);
//            paintBorder.setAntiAlias(true);
//        }
//        paintBg = new Paint();
//        paintBg.setColor(bgColor);
//        paintBg.setAntiAlias(true);
//        paintBg.setStyle(Paint.Style.FILL);
//
//        if (radius == 0 && shapeType == GradientDrawable.RECTANGLE) {
//            mPath = new Path();
//            radiusf = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius,
//                    bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};
//        }
//
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d(tag, "onMeasure: ");
//        mReactf.set(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth);
//        if (mPath != null) {
//            mPath.addRoundRect(mReactf, radiusf, Path.Direction.CW);
//        }
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Log.d(tag, "onDraw: ");
//        if (shapeType == GradientDrawable.RECTANGLE) {//四角形
//            if (radius == 0) {
//                canvas.drawPath(mPath, paintBg);
//            } else {
//                canvas.drawRoundRect(mReactf, radius, radius, paintBg);
//                if (paintBorder != null) {
//                    if (borderDashGap > 0) {//设置虚线
//                        PathEffect effects = new DashPathEffect(new float[]{borderDashLength,
//                                borderDashLength, borderDashGapSmall, borderDashLength}, borderDashGap);//设置虚线的间隔和点的长度
//                        paintBorder.setPathEffect(effects);
//                    }
//                    canvas.drawRoundRect(mReactf, radius, radius, paintBorder);
//                }
//            }
//        } else {//椭圆形
//            canvas.drawOval(mReactf, paintBg);
//            if (paintBorder != null) {
//                if (borderDashGap > 0) {//设置虚线
//                    PathEffect effects = new DashPathEffect(new float[]{borderDashLength,
//                            borderDashLength, borderDashGapSmall, borderDashLength}, borderDashGap);
//                    paintBorder.setPathEffect(effects);
//                }
//                canvas.drawOval(mReactf, paintBorder);
//            }
//        }
//        super.onDraw(canvas);//需要在自己绘制边框后绘制，否则会被覆盖掉
//    }
//
//
//}
