package com.example.lijinduo.mydemo.redbag;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    /**
     * 概率列表
     */
    private List<Integer> probabilityList = new ArrayList<>();
    /**
     * 10秒控制线程开启
     */
    private boolean isStart = true;
    /**
     * 红包扩充器
     */
    private RelativeLayout.LayoutParams lp;
    /**
     * 背景
     */
    private RelativeLayout.LayoutParams lp_bg;
    /**
     * 左下角红包
     */
    private ImageView ivLeftBottom;
    /**
     * 右上角倒计时
     */
    private ImageView ivRightTop;
    /**
     * 321倒计时
     */
    private RelativeLayout.LayoutParams lpCountdown;
    /**
     * 10987654321倒计时图片
     */
    private RelativeLayout.LayoutParams lpCountdownTen;
    /**
     * 左下角红包
     */
    private RelativeLayout.LayoutParams lpImg;
    /**
     * 组件宽
     */
    private int viewWith;
    /**
     * 组件高
     */
    private int viewHeight;
    /**
     * 初始X位置
     */
    private int startX;
    /**
     * 控制是321倒计时还是计时红包雨时间
     */
    private boolean isFirst = true;
    /**
     * 321倒计时秒数
     */
    private int change = 4;
    /**
     * 倒计时imageView
     */
    private ImageView imgCountdown;
    /**
     * 爆炸组件
     */
    private ExplosionField destroy;
    /**
     * 选中个数回调
     */
    private SelectNumFace selectNumFace;
    /**
     * 选中红包个数
     */
    private int selectNum = 0;


    private Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                imgCountdown.setImageResource(0);
                int itemlinNum = new Random().nextInt(2) + 3;
                int itemNum = new Random().nextInt(change > 4 ? itemlinNum : change) + 1;
                for (int i = 0; i < itemNum; i++) {
                    startX = new Random().nextInt(viewWith - 200) + 100;
                    lp = new RelativeLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    final ImageView img = new ImageView(context);
//                    img.setImageResource(R.drawable.money_baobao);
                    Glide.with(context).load(R.drawable.money_baobao).into(img);
                    //img大小
                    final int size = new Random().nextInt(70) + 150;
                    //摇摆幅度
                    int swingX = new Random().nextInt(500) - 250;
                    //摇摆位置
                    int swing = startX + swingX;
                    if (swing < 0 || swing > viewWith - size) {
                        swing = startX - swingX;
                    }
                    lp.width = size;
                    lp.height = size;
                    img.setLayoutParams(lp);
                    img.setTranslationX(startX);
                    ObjectAnimator animtorCurTranslationY = ObjectAnimator.ofFloat(img, "translationY", -size, viewHeight + 300);
                    ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(img, "translationX", startX, swing);
                    final AnimatorSet animSet = new AnimatorSet();
                    animtorCurTranslationY.setDuration(new Random().nextInt(1500) + 3000);
                    animtorCurTranslationX.setDuration(new Random().nextInt(2300) + 2000);
                    animSet.play(animtorCurTranslationY).with(animtorCurTranslationX);
                    animSet.start();
                    img.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (probabilityList.size() < 1) {
                                destroy.explode(img);
//                                destroy.clearAnimation();
                                animSet.cancel();
                                img.clearAnimation();
                                removeView(img);
                            } else {
                                if (new Random().nextInt(100) < probabilityList.get(0)) {
                                    img.setImageResource(R.drawable.light_redbag);
                                    selectNum++;
                                    ivLeftBottom.setVisibility(VISIBLE);
                                    switch (selectNum){
                                        case 1:
                                            ivLeftBottom.setImageResource(R.drawable.rd_num1);
                                            break;
                                        case 2:
                                            ivLeftBottom.setImageResource(R.drawable.rd_num2);
                                            break;
                                        case 3:
                                            ivLeftBottom.setImageResource(R.drawable.rd_num3);
                                            break;
                                    }
                                    ObjectAnimator animtorScaleXRedBag = ObjectAnimator.ofFloat(ivLeftBottom, "scaleX", 1f, 1.5f, 1f);
                                    ObjectAnimator animtorScaleYRedBag = ObjectAnimator.ofFloat(ivLeftBottom, "scaleY", 1f, 1.5f, 1f);
                                    animtorScaleXRedBag.setDuration(350);
                                    animtorScaleYRedBag.setDuration(350);
                                    final AnimatorSet animSetRedBag = new AnimatorSet();
                                    animSetRedBag.play(animtorScaleXRedBag).with(animtorScaleYRedBag);
                                    animSet.cancel();
                                    img.clearAnimation();
                                    ObjectAnimator animtorCurTranslationY = ObjectAnimator.ofFloat(img, "translationY", viewHeight - 200);
                                    ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(img, "translationX", 150 - size / 2);
                                    ObjectAnimator animtorScaleX = ObjectAnimator.ofFloat(img, "scaleX", 1f, 0.2f);
                                    ObjectAnimator animtorScaleY = ObjectAnimator.ofFloat(img, "scaleY", 1f, 0.2f);
                                    ObjectAnimator animtorAlpha = ObjectAnimator.ofFloat(img, "alpha", 1f, 0.3f);
                                    animtorCurTranslationY.addListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            animSet.cancel();
                                            img.clearAnimation();
                                            removeView(img);
                                            animSetRedBag.start();
                                        }
                                    });
                                    AnimatorSet animSet = new AnimatorSet();
                                    animtorCurTranslationY.setDuration(500);
                                    animtorCurTranslationX.setDuration(500);
                                    animtorScaleX.setDuration(500);
                                    animtorScaleY.setDuration(500);
                                    animSet.play(animtorCurTranslationY).with(animtorCurTranslationX).with(animtorScaleX).with(animtorScaleY).with(animtorAlpha);
                                    animSet.start();
                                    probabilityList.remove(0);
                                } else {
                                    destroy.explode(img);
                                    animSet.cancel();
                                    img.clearAnimation();
                                    removeView(img);
                                }
                            }
                        }
                    });
                    addView(img);
                }
                ivRightTop.setVisibility(VISIBLE);
                //替换倒计时图片
                switch (11-change) {
                    case 0:
                        ivRightTop.setVisibility(GONE);
                        break;
                    case 1:
                        ivRightTop.setImageResource(R.drawable.countdown1);
                        break;
                    case 2:
                        ivRightTop.setImageResource(R.drawable.countdown2);
                        break;
                    case 3:
                        ivRightTop.setImageResource(R.drawable.countdown3);
                        break;
                    case 4:
                        ivRightTop.setImageResource(R.drawable.countdown4);
                        break;
                    case 5:
                        ivRightTop.setImageResource(R.drawable.countdown5);
                        break;
                    case 6:
                        ivRightTop.setImageResource(R.drawable.countdown6);
                        break;
                    case 7:
                        ivRightTop.setImageResource(R.drawable.countdown7);
                        break;
                    case 8:
                        ivRightTop.setImageResource(R.drawable.countdown8);
                        break;
                    case 9:
                        ivRightTop.setImageResource(R.drawable.countdown9);
                        break;
                    case 10:
                        ivRightTop.setImageResource(R.drawable.countdown10);
                        break;
                }
                postInvalidate();
                if (change == 11) {
                    isStart = false;
                    selectNumFace.select(selectNum);
                }
            }
            if (msg.what == 0x234) {
                ObjectAnimator animtorScaleX = ObjectAnimator.ofFloat(imgCountdown, "scaleX", 1f, 0.3f);
                ObjectAnimator animtorScaleY = ObjectAnimator.ofFloat(imgCountdown, "scaleY", 1f, 0.3f);
                ObjectAnimator animtoralpha = ObjectAnimator.ofFloat(imgCountdown, "alpha", 1f, 0.3f);
                animtorScaleX.setDuration(1000);
                animtorScaleY.setDuration(1000);
                animtoralpha.setDuration(1000);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(animtorScaleX).with(animtorScaleY).with(animtoralpha);
                animSet.start();
                if (change == 0) {
                    imgCountdown.setImageResource(R.drawable.djs_go);
                } else {
                    switch (change) {
                        case 1:
                            imgCountdown.setImageResource(R.drawable.djs_1);
                            break;
                        case 2:
                            imgCountdown.setImageResource(R.drawable.djs_2);
                            break;
                        case 3:
                            imgCountdown.setImageResource(R.drawable.djs_3);
                            break;
                    }
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
        Log.d("isStart", "run: 1111111");
        lp_bg = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //背景图

        ImageView imageviewBg = new ImageView(context);
//        imageviewBg.setImageResource(R.drawable.djs_bg);
        imageviewBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageviewBg.setLayoutParams(lp_bg);
        Glide.with(context).load(R.drawable.djs_bg).into(imageviewBg);
        addView(imageviewBg);
        //321倒计时
        lpCountdown = new RelativeLayout.LayoutParams(
                300, 300);
        imgCountdown = new ImageView(context);
        lpCountdown.addRule(RelativeLayout.CENTER_IN_PARENT);
        imgCountdown.setLayoutParams(lpCountdown);
        addView(imgCountdown);
        //左下角红包
        lpImg = new RelativeLayout.LayoutParams(
                209, 225);
        lpImg.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        ivLeftBottom = new ImageView(context);
        ivLeftBottom.setVisibility(GONE);
        ivLeftBottom.setImageResource(R.drawable.light_redbag);
        ivLeftBottom.setLayoutParams(lpImg);
        addView(ivLeftBottom);
        destroy = ExplosionField.attach2WindowViewGroup(this, context);
        //10秒倒计时
        lpCountdownTen = new RelativeLayout.LayoutParams(
                100, 100);
        lpCountdownTen.addRule(ALIGN_PARENT_RIGHT);
        lpCountdownTen.setMargins(0,10,20,0);
        ivRightTop = new ImageView(context);
        ivRightTop.setVisibility(GONE);
        ivRightTop.setLayoutParams(lpCountdownTen);
        addView(ivRightTop);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (isStart) {
            if (viewWith != 0) {
                if (isFirst) {
                    hander.sendEmptyMessage(0x234);
                    change--;
                    if (change == 0) {
                        isFirst = false;
                    }
                } else {
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("isStart", "run: 2222222");
        viewWith = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        if (viewWith != 0) {
            startX = new Random().nextInt(viewWith - 200) + 100;
        }
    }

    public void setStop(boolean isStart){
        this.isStart=isStart;
        removeAllViews();
    }


    /**
     * 后台注入概率列表
     *
     * @param probabilityList
     */
    public void setProbabilityList(List<Integer> probabilityList) {

        this.probabilityList = probabilityList;

    }

    public void setSelectNum(SelectNumFace selectNumFace) {
        this.selectNumFace = selectNumFace;
    }


    public interface SelectNumFace {
        void select(int num);
    }


}
