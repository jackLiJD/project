package com.example.lijinduo.mydemo.todaynews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/10
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ConflictScrollView extends ScrollView {
    private GestureDetector mGestureDetector;
    public ConflictScrollView(Context context) {
        this(context,null);
    }

    public ConflictScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }


    /**
     * 触发拦截触摸事件
     * 一但返回True（代表事件在当前的viewGroup中会被处理），则向下传递之路被截断（所有子控件将没有机会参与Touch事件），
     * 同时把事件传递给当前的控件的onTouchEvent()处理；返回false，则把事件交给子控件的onInterceptTouchEvent()
     *
     *
     * onInterceptTouchEvent方法是关键，重写这个方法使如果ScrollView有touch事件时不被拦截，
     * 这样只要ScrollView有touch事件优先处理，这样就保证了滑动的流畅。
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
