package com.example.lijinduo.mydemo.tool;

import android.os.Handler;
import android.util.Log;

import static com.example.lijinduo.mydemo.tool.MyApplication.getMainThreadId;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class AppTool {
    public static void log(String string){
            Log.d("MyData", "log >> \n"+string);
    }
    public static void log(String TAG,String string){
        Log.d(TAG, "log >> \n"+string);
    }
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }
    /**
     * 判断是否运行在主线程
     *
     * @return true：当前线程运行在主线程
     * fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

}
