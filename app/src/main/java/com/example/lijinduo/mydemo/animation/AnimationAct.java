package com.example.lijinduo.mydemo.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 * 链接：http://www.jianshu.com/p/0af106016140
 * 來源：简书
 */
public class AnimationAct extends BaseActivity {
    Button animation_start,startAnimatorPath;
    Button animation_xml_start, animation_start_time;
    int i = 0;
    long time = 0;
    private Button fab;
    private AnimatorPath path;//声明动画集合
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation);
        animation_xml_start = (Button) findViewById(R.id.animation_xml_start);
        animation_start = (Button) findViewById(R.id.animation_start);
        animation_start_time = (Button) findViewById(R.id.animation_start_time);
        startAnimatorPath = (Button) findViewById(R.id.startAnimatorPath);
        animation_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(i++);
//                Rotate();
            }
        });
        animation_xml_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startXmlAnim();
            }
        });
        animation_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimTime();
            }
        });
        setPath();
        startAnimatorPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimatorPath(fab, "fab", path);
            }
        });

    }

    /**
     * 设置动画启动次数
     */
    private void startAnimTime() {
        final ObjectAnimator animatorX = ObjectAnimator.ofFloat(animation_start_time, "scaleX", 1f, 0.9f, 1f);
        final ObjectAnimator animatorY = ObjectAnimator.ofFloat(animation_start_time, "scaleY", 1f, 0.9f, 1f);
        //设置额外重复播放的次数n+1 负1未无限循环
//        animator.setRepeatCount(1);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(300);
//      设置循环模式，有两种，RESTRAT重新播放，REVERSE倒序播放 animSet.setRepeatMode(ValueAnimator.REVERSE);
//      这玩意设置不了次数  animSet.setRepeatCount(ValueAnimator.INFINITE);
        //时间插值器
        animSet.setInterpolator(new AccelerateInterpolator());
        animSet.play(animatorX).with(animatorY);
        animSet.start();
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画可以cancel
                animatorX.cancel();
                animation_start_time.clearAnimation();
                Log.d("动画销毁", animatorX + "onAnimationEnd: ");
            }
        });

    }


    /**
     * xml配置动画
     */
    private void startXmlAnim() {
        Animator animator = AnimatorInflater.loadAnimator(AnimationAct.this, R.animator.group_anim);

        animator.setTarget(animation_xml_start);
        animator.start();

    }

    /**
     * @param i after(Animator anim)   将现有动画插入到传入的动画之后执行
     *          <p>
     *          after(long delay)   将现有动画延迟指定毫秒后执行
     *          <p>
     *          before(Animator anim)   将现有动画插入到传入的动画之前执行
     *          <p>
     *          with(Animator anim)   将现有动画和传入的动画同时执行
     */
    private void startAnimation(int i) {
        float curTranslationX = animation_start.getTranslationX();
        //渐变
        ObjectAnimator animtorAlpha = ObjectAnimator.ofFloat(animation_start, "alpha", 1f, 0f, 1f, 0.5f, 1f);
        //旋转
        ObjectAnimator animtorRotation = ObjectAnimator.ofFloat(animation_start, "rotationY", 0f, 360f);
        //平移
        ObjectAnimator animtorCurTranslationX = ObjectAnimator.ofFloat(animation_start, "translationX", curTranslationX, -500f, 500f, curTranslationX);
        //缩放
        ObjectAnimator animtorScaleY = ObjectAnimator.ofFloat(animation_start, "scaleY", 1f, 3f, 1f);
        //缩放
        ObjectAnimator animtorScaleX = ObjectAnimator.ofFloat(animation_start, "scaleX", 1f, 3f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        switch (i) {
            case 0:
                animSet.play(animtorAlpha).before(animtorRotation);
                animtorAlpha.setDuration(3000);
                animtorRotation.setDuration(5000);
//                animSet.setDuration(2000);
                animSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.d("动画开始", "onAnimationEnd: " + System.currentTimeMillis());
                        time = System.currentTimeMillis();

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.d("动画完毕", "onAnimationEnd: " + (System.currentTimeMillis() - time));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                //特别强调 动画启动start前  要设置好监听否者获取不到启动时间
                animSet.start();
                break;
            case 1:
                animtorRotation.setRepeatCount(5);
                animSet.setInterpolator(new LinearInterpolator());
                animSet.play(animtorRotation);
                animSet.setDuration(300);
                animSet.start();
                break;

            case 2:
                animSet.play(animtorScaleY).with(animtorScaleX);
                animSet.setDuration(2000);
                animSet.start();
                break;

            case 3:
                animtorScaleY.setDuration(2000);
                animtorScaleY.start();
                break;
            case 4:
                animtorCurTranslationX.setDuration(2000);
                animtorCurTranslationX.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Log.d("动画播放完毕", "onAnimationEnd: ");
                    }
                });
                animtorCurTranslationX.start();
                break;

        }


    }


    //旋转
    public void Rotate(){
        ObjectAnimator animtorRotation = ObjectAnimator.ofFloat(animation_start, "rotation", 0f, 360f);
        animtorRotation.setRepeatCount(-1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setInterpolator(new LinearInterpolator());
        animSet.play(animtorRotation);
        animSet.setDuration(300);
        animSet.start();
    }

    /*设置动画路径*/
    public void setPath(){
        path = new AnimatorPath();
        path.moveTo(0,0);
        path.lineTo(400,400);
        path.secondBesselCurveTo(600, 200, 800, 400); //订单
        path.thirdBesselCurveTo(100,600,900,1000,200,1200);
    }

    /**
     * 设置动画
     * @param view
     * @param propertyName
     * @param path
     */
    private void startAnimatorPath(View view, String propertyName, AnimatorPath path) {
        ObjectAnimator anim = ObjectAnimator.ofObject(this, propertyName, new PathEvaluator(), path.getPoints().toArray());
        anim.setInterpolator(new DecelerateInterpolator());//动画插值器
        anim.setDuration(3000);
        anim.start();
    }

    /**
     * 设置View的属性通过ObjectAnimator.ofObject()的反射机制来调用
     * @param newLoc
     */
    public void setFab(PathPoint newLoc) {
        fab.setTranslationX(newLoc.mX);
        fab.setTranslationY(newLoc.mY);
    }

}
