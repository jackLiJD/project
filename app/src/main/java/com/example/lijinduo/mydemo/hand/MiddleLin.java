package com.example.lijinduo.mydemo.hand;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/30
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MiddleLin extends LinearLayout {
    public MiddleLin(Context context) {
        super(context);
    }

    public MiddleLin(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //是否消费事件 如果为false 把事件返回给父布局
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
//        Log.d("onTouchEvent", "onTouchEvent: here11111");
//        //执行父类的方法 如点击事件onclick效果
////        return super.onTouchEvent(event);
//        //消费监听
////        return true;
//        //事件不消费返回给父布局
//        return true;
//    }
////
////
    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        super.onInterceptTouchEvent(ev);
////        return super.onInterceptTouchEvent(ev);
////        true表示拦截  fale 表示向下传递
//        return false;
//    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        super.dispatchTouchEvent(ev);
////        return super.dispatchTouchEvent(ev);
//        //true 是向内部执行
//        //false 是向父容器传递事件
//        return false;
//    }
}
