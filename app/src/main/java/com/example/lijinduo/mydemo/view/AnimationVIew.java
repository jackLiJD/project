package com.example.lijinduo.mydemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class AnimationVIew extends RelativeLayout implements ValueAnimator.AnimatorUpdateListener {
    Path path;
    Path pathDst;
    Paint paint;
    private PathMeasure mPathMeasure;
    private float animatorProgress;
    private ValueAnimator mRightAnimator;
    private Context context;
    private boolean first=true;
    private int n;

    public AnimationVIew(Context context) {
        this(context, null);
    }

    public AnimationVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mRightAnimator = ValueAnimator.ofFloat(0, 1);
        mRightAnimator.setDuration(1000);
        mRightAnimator.addUpdateListener(this);
        mRightAnimator.start();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        path = new Path();
        pathDst = new Path();
        path.moveTo(120, 80);
        path.lineTo(180, 80);

        path.moveTo(140, 70);
        path.lineTo(140, 90);

        path.moveTo(160, 70);
        path.lineTo(160, 90);

        path.moveTo(120, 110);
        path.lineTo(180, 110);
        path.lineTo(170, 155);
        path.lineTo(160, 140);

        path.moveTo(150, 95);
        path.lineTo(110, 155);

        path.moveTo(110, 125);
        path.lineTo(90, 135);

        path.moveTo(190, 125);
        path.lineTo(210, 135);



        path.moveTo(250,90);
        path.lineTo(260,100);

        path.moveTo(250,110);
        path.lineTo(260,120);

        path.moveTo(255,150);
        path.lineTo(260,130);


        path.moveTo(280,90);
        path.lineTo(340,90);

        path.moveTo(300,120);
        path.lineTo(320,120);

        path.moveTo(310,90);
        path.lineTo(310,155);

        path.moveTo(280,155);
        path.lineTo(340,155);



        path.moveTo(350,90);
        path.lineTo(360,100);

        path.moveTo(350,110);
        path.lineTo(360,120);

        path.moveTo(355,150);
        path.lineTo(360,130);


        path.moveTo(380,90);
        path.lineTo(440,90);

        path.moveTo(400,120);
        path.lineTo(420,120);

        path.moveTo(410,90);
        path.lineTo(410,155);

        path.moveTo(380,155);
        path.lineTo(440,155);




        mPathMeasure = new PathMeasure(path, false);
        if (first){
            Path pathHand=new Path();
            pathHand.addPath(path,0,-100);
            RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(
                    100, 100);
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(R.drawable.asdasd);
            imageView.setLayoutParams(params);
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                addView(imageView);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", "translationY",pathHand);
                objectAnimator.setDuration(15000).start();
            }

            first=false;
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (animatorProgress == 1 && mPathMeasure.getLength() != 0) {
            n=n+1;
            if (n==2||n==3||n==6||n==7||n==8||n==9||n==10||n==12||n==15||n==16||n==17||n==19) {

                mRightAnimator.setDuration(300);
            }else if (n==4){
                mRightAnimator.setDuration(2000);
            }else{
                mRightAnimator.setDuration(1000);
            }
            mPathMeasure.nextContour();
            mRightAnimator.start();
        }
        mPathMeasure.getSegment(0, animatorProgress * mPathMeasure.getLength(), pathDst, true);
        canvas.drawPath(pathDst, paint);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        animatorProgress = (float) animation.getAnimatedValue();
        invalidate();
    }
}
