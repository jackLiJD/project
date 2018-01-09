package com.example.lijinduo.mydemo.todaynews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ClipViewPager extends ViewPager {

    //默认距离
    private final static float DISTANCE = 10;
    private float downX;
    private float downY;

    public ClipViewPager(Context context) {
        super(context);
    }

    public ClipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            downX = ev.getX();
            downY = ev.getY();
        }else if (ev.getAction() == MotionEvent.ACTION_UP) {

            float upX = ev.getX();
            float upY = ev.getY();
            //如果 up的位置和down 的位置 距离 > 设置的距离,则事件继续传递,不执行下面的点击切换事件
            if(Math.abs(upX - downX) > DISTANCE || Math.abs(upY - downY) > DISTANCE){
                return super.dispatchTouchEvent(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
