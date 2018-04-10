package com.example.lijinduo.mydemo.book;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ScrollWeb extends CacheWebView {
    private DetailWebScrollListener listener;

    public ScrollWeb(Context context) {
        super(context);
    }

    public ScrollWeb(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDetialListener(DetailWebScrollListener listener) {
        this.listener = listener;
    }

    public interface DetailWebScrollListener {

        void onTop();

        void onNoTop();


    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        Log.d("数据", clampedY + "onOverScrolled: " + scrollY);

        if (scrollY == 0 && clampedY) {
            if (listener != null) {
                listener.onTop();
            }
        } else {
            if (listener != null) {
                if (scrollY > 10) {
                    listener.onNoTop();
                }

            }
        }
    }
}
