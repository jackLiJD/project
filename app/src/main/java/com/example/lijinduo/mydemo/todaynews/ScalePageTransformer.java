package com.example.lijinduo.mydemo.todaynews;

import android.os.Build;
import android.support.v4.view.ViewPager;
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
public class ScalePageTransformer implements ViewPager.PageTransformer {

    //中间大  左右小
    public   float MAX_SCALE = 1.2f;
    public   float MIN_SCALE = 0.6f;

    public ScalePageTransformer(int type) {
        switch (type){
            case 1:
               MAX_SCALE = 1.2f;
               MIN_SCALE = 0.6f;
                break;
            case 2:
                MAX_SCALE = 0.8f;
                MIN_SCALE = 0.8f;
                break;
            case 3:
                MAX_SCALE = 1f;
                MIN_SCALE = 1f;
                break;
        }
    }
    //等大
//public static final float MAX_SCALE = 0.8f;
//    public static final float MIN_SCALE = 0.8f;

    //链接等大
//public static final float MAX_SCALE = 1f;
//    public static final float MIN_SCALE = 1f;

    @Override
    public void transformPage(View page, float position) {

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }
}
