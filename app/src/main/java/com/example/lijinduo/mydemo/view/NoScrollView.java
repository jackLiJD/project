package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/26
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class NoScrollView extends ScrollView {
    String TAG="手势";
    int y=0;
    public spaceY spaceY;

    public NoScrollView(Context context) {
        super(context);
    }

    public NoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int moveY=0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y= (int) event.getRawY();
                Log.d(TAG, "onTouch: "+y);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY=(int) event.getRawY()-y;
                Log.d(TAG, event.getRawY()+"==="+y+"===="+moveY);
                y=(int) event.getRawY();
                scrollBy(0,2*moveY);
                spaceY.space(moveY);
                break;
        }
        return true;
    }

    public void setCallback(spaceY spaceY){
        this.spaceY=spaceY;
    }

    public interface spaceY{
        void space(int y);
    }





}
