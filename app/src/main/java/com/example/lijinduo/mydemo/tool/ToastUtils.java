package com.example.lijinduo.mydemo.tool;

import android.content.Context;
import android.widget.Toast;

import ren.yale.android.cachewebviewlib.utils.AppUtils;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ToastUtils {
    private static Toast mToast = null;

    /**
     * 显示一个toast提示
     *
     * @param resouceId toast字符串资源id
     */
    public static void showToast(int resouceId) {
        showToast(MyApplication.getContext().getResources().getString(resouceId));
    }

    /**
     * 显示一个toast提示
     *
     * @param text toast字符串
     */
    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个toast提示
     *
     * @param text     toast字符串
     * @param duration toast显示时间
     */
    public static void showToast(String text, int duration) {
        showToast(MyApplication.getContext(), text, duration);
    }

    /**
     * 显示一个toast提示
     *
     * @param context  context 上下文对象
     * @param text     toast字符串
     * @param duration toast显示时间
     */
    public static void showToast(final Context context, final String text, final int duration) {
        /**
         * 保证运行在主线程
         */
        AppTool.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context, text, duration);
                } else {
                    mToast.setText(text);
                    mToast.setDuration(duration);
                }
                mToast.show();
            }
        });
    }
}
