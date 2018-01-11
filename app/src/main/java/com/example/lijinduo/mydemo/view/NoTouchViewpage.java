package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.lijinduo.mydemo.todaynews.ClipViewPager;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class NoTouchViewpage extends ClipViewPager {
    public NoTouchViewpage(Context context) {
        super(context);
    }

    public NoTouchViewpage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//         super.onTouchEvent(event);
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return false;
    }

}
