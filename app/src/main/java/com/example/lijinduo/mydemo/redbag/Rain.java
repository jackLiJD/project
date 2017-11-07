package com.example.lijinduo.mydemo.redbag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;

import java.util.Random;

import tyrantgit.explosionfield.ExplosionField;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Rain extends RelativeLayout implements Runnable {
    private Context context;
    private boolean isStart = true;
    private RelativeLayout.LayoutParams lp;
    private RelativeLayout.LayoutParams lpText;
    private int viewWith;
    private int viewHeight;
    private int startX;
    private boolean isFirst=true;
    private int change=4;
    private TextView textview;
    private ExplosionField destroy;
    private Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("change", "handleMessage: "+change);
            if (msg.what == 0x123) {
                textview.setText("");
                int itemlinNum=new Random().nextInt(2)+3;
                int itemNum=new Random().nextInt(change>4?itemlinNum:change)+1;
                Log.d("change",itemNum+ "handleMessage: "+change);
                for (int i = 0; i < itemNum; i++) {
                    startX = new Random().nextInt(viewWith - 200) + 100;
                    lp = new RelativeLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    final ImageView img = new ImageView(context);
                    img.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
                    //img大小
                    int size=new Random().nextInt(100)+100;
                    //摇摆幅度
                    int swing=new Random().nextInt(500)-250+startX;
                    //旋转角度
                    int rorate=new Random().nextInt(90)-45;
                    lp.width = size;
                    lp.height = size;
                    img.setLayoutParams(lp);
                    img.setTranslationX(startX);
                    //旋转
                    ObjectAnimator animtorRotation = ObjectAnimator.ofFloat(img, "rotation", 0f, rorate);
                    ObjectAnimator animtorCurTranslationY = ObjectAnimator.ofFloat(img, "translationY", -size,viewHeight);
                    ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(img, "translationX", startX,swing);
                    AnimatorSet animSet = new AnimatorSet();
                    animtorCurTranslationY.setDuration(new Random().nextInt(3000)+1500);
                    animtorCurTranslationX.setDuration(new Random().nextInt(2300)+800);
                    animtorCurTranslationY.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            removeView(img);
                        }
                    });
                    animSet.play(animtorCurTranslationY).with(animtorCurTranslationX).with(animtorRotation);
                    animSet.start();
                    img.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            destroy.explode(img);
                            removeView(img);
                        }
                    });
                    addView(img);
                }
                postInvalidate();
                if (change == 10) {
                    Toast.makeText(context,"GoodBy",200).show();
                    isStart=false;
                }
            }
            if (msg.what == 0x234) {
                ObjectAnimator animtorScaleX = ObjectAnimator.ofFloat(textview, "scaleX", 1f, 0.3f);
                ObjectAnimator animtorScaleY = ObjectAnimator.ofFloat(textview, "scaleY", 1f, 0.3f);
                ObjectAnimator animtoralpha = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0.3f);
                animtorScaleX.setDuration(1000);
                animtorScaleY.setDuration(1000);
                animtoralpha.setDuration(1000);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(animtorScaleX).with(animtorScaleY).with(animtoralpha);
                animSet.start();
                if (change==0){
                    textview.setText("GO");
                }else{
                    textview.setText(change+"");
                }
                postInvalidate();
            }
        }
    };
    public Rain(Context context) {
        super(context);
        this.context = context;

    }
    public Rain(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }
    private void initView() {
         destroy= ExplosionField.attach2Window((Activity) context);
        lpText = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textview=new TextView(context);
        textview.setTextSize(150);
        textview.setTextColor(Color.BLACK);
        textview.setGravity(CENTER_IN_PARENT);
        lpText.addRule(RelativeLayout.CENTER_IN_PARENT);
        textview.setLayoutParams(lpText);
        addView(textview);
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (isStart) {
            if (isFirst) {
                hander.sendEmptyMessage(0x234);
                change--;
                if (change == 0) {
                    isFirst=false;
                }
            }else{
                change++;
                hander.sendEmptyMessage(0x123);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWith = getWidth();
        viewHeight = getHeight();
        if (viewWith != 0) {
            startX = new Random().nextInt(viewWith - 200) + 100;
        }
    }

    //开启功能
    private void setStart(boolean isStart) {
        this.isStart = isStart;
    }
}
