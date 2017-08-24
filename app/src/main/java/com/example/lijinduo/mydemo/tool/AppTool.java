package com.example.lijinduo.mydemo.tool;

import android.util.Log;

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
}
