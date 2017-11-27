package com.example.lijinduo.mydemo.redbag.yanhua;

import android.graphics.PixelFormat;
import android.view.SurfaceView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class HolderSurfaceView {
    private HolderSurfaceView() {

    }

    private static HolderSurfaceView mHolderSurfaceView = null;

    public static HolderSurfaceView getInstance() {
        if (mHolderSurfaceView == null)
            mHolderSurfaceView = new HolderSurfaceView();
        return mHolderSurfaceView;
    }

    private SurfaceView mSurfaceView;

    /**
     * 设置SurfaceView
     *
     * @param view
     */
    public void setSurfaceView(SurfaceView view) {
        mSurfaceView = view;
//        mSurfaceView.setZOrderOnTop(true);
        mSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public SurfaceView getSurfaceView() {
        return mSurfaceView;
    }
}