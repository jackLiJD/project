package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class NoTouchImageView extends LinearLayout {
    public NoTouchImageView(Context context) {
        super(context);
    }

    public NoTouchImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) { // 屏蔽touch事件,才能在监听其子控件的touch事件
//        // TODO Auto-generated method stub
////        super.onTouchEvent(ev);
//        return false;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event)// 屏蔽touch事件传递,才能在监听其子控件的touch事件
//    {
////        super.onInterceptTouchEvent(event);
//        return false;
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
//        return super.dispatchTouchEvent(ev);
        return   true;
        //false 是向父容器传递事件  true是向内部执行
//        return false;
    }


}
