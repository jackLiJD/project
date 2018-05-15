package com.example.lijinduo.mydemo.sea;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lijinduo.mydemo.R;

import java.util.List;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/17
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class SeaManView extends RelativeLayout{

    ImageView imageView;
    Path path;
    private Paint paint;
    private int baoabaoHeight=150;
    public SeaManView(Context context) {
        this(context,null);
        setWillNotDraw(false);
    }

    public SeaManView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(
                baoabaoHeight, baoabaoHeight);
        imageView=new ImageView(context);
        imageView.setImageResource(R.drawable.money_baobao);
        imageView.setLayoutParams(params);
        addView(imageView);
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (path != null) {
            canvas.drawPath(path,paint);
        }
    }
    public void setData(List<Integer> list,int width){
        path=new Path();
        path.moveTo(100,list.get(2)-baoabaoHeight/2);
        path.lineTo(width-100-100,list.get(1)-baoabaoHeight/2);
        path.lineTo(100,list.get(0)-baoabaoHeight/2);
        ObjectAnimator animtorCurTranslationY = ObjectAnimator.ofFloat(imageView, "translationY", list.get(2)-baoabaoHeight/2, list.get(1)-baoabaoHeight/2 ,list.get(0)-baoabaoHeight/2);
        ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(imageView, "translationX", 100, width-100-100,100);
        final AnimatorSet animSet = new AnimatorSet();
        animtorCurTranslationY.setDuration(3000);
        animtorCurTranslationX.setDuration(3000);
        animSet.play(animtorCurTranslationY).with(animtorCurTranslationX);
        animSet.start();
        postInvalidate();
    }


}
