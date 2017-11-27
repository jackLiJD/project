package com.example.lijinduo.mydemo.redbag;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.lijinduo.mydemo.R;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/8
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Rolling extends FrameLayout {
    private FrameLayout.LayoutParams layoutParams;
    private ImageView imageView;

    public Rolling(@NonNull Context context) {
        super(context);
    }

    public Rolling(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.timi);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getWidth() != 0) {
            layoutParams.height = getHeight();
            layoutParams.width = getHeight()*640/360;
            postInvalidate();
            ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(imageView, "translationX", 0, -getHeight()/360*280,0);
            ObjectAnimator animtorScaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.05f, 1,1.05f);
            ObjectAnimator animtorScaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.05f, 1f,1.05f);
            animtorCurTranslationX.setRepeatCount(-1);
            animtorScaleX.setRepeatCount(-1);
            animtorScaleY.setRepeatCount(-1);
            animtorScaleX.setDuration(5000);
            animtorScaleY.setDuration(5000);
            animtorCurTranslationX.setDuration(10000);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animtorCurTranslationX).with(animtorScaleX).with(animtorScaleY);
            animSet.start();
        }

    }
}
