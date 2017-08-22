package com.example.lijinduo.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
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
public class MyBindService extends Service{
    String TAG="绑定service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
    class  MyBinder extends Binder{

        public void  doSomething(){
            Log.d(TAG, "doSomething: ");
        }

        public void openString(){
            Log.d(TAG, "openString: ");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
