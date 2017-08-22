package com.example.lijinduo.mydemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/22
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MyIntentService extends IntentService{
    public MyIntentService() {
        super("myIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for(int i = 0;i<3;i++) {
            //Service要执行的逻辑
            //这里我们只打印当前线程的id
            Log.d("MyIntentService","IntentService线程的id是："+Thread.currentThread().getId());
            try {
                //线程睡眠一秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy");
    }
}
