package com.example.lijinduo.mydemo.service;

import android.app.Service;
import android.content.Intent;
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
public class ServiceTest extends Service{
    public String TAG="ServiceTest";
    //onBind()方法是Service中唯一的一个抽象方法，所以必须要在子类里实现。
    //Service有两种启动方式：一种是startService()，另一种是bindService()。第二种启动方式才会用到onBind()方法。
    //我们这先用第一种方式定义Service，所以暂时忽略onBind()方法。
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    //onCreate()方法只会在Service第一次被创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }
    //onStartCommand()方法在每次启动服务的时候都会调用。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("线程id", "onStartCommand: "+Thread.currentThread().getId());
        //停止一个started服务有两种方法：
        //（1）在外部使用stopService()手动停止。
        //（2）在服务内部(onStartCommand方法内部)使用stopSelf()方法，使服务执行完毕后自动停止
//        stopSelf();
        Log.d(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("线程id", "Thread: "+Thread.currentThread().getId());
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
      /*  onStartCommand方法执行时，返回的是一个int型。这个整型可以有三个返回值：START_NOT_STICKY、START_STICKY、START_REDELIVER_INTENT
        START_NOT_STICKY:“非粘性的”。使用这个返回值时，如果在执行完onStartCommand方法后，服务被异常kill掉，系统不会自动重启该服务。
        START_STICKY：如果Service进程被kill掉，保留Service的状态为开始状态，但不保留递送的intent对象。随后系统会尝试重新创建Service，由于服务
        状态为开始状态，所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。如果在此期间没有任何启动命令被传递到Service，那么参数Intent将为null。
        START_REDELIVER_INTENT：重传Intent。使用这个返回值时，系统会自动重启该服务，并将Intent的值传入。
*/
    
    }

    //onDestroy()方法只会在Service第一次被停止的时候调用，多次点击停止不会报异常，也不再执行onDestroy()方法。
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
