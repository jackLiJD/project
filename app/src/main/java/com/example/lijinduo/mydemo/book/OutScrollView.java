package com.example.lijinduo.mydemo.book;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class OutScrollView extends ScrollView {
    public OutScrollView(Context context) {
        super(context);
    }

    public OutScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
    }

    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//
//
//        return false;
//    }
}
