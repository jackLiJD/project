package com.example.lijinduo.mydemo.tool;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.widget.ImageView;
import android.widget.TextView;

//import com.facebook.drawee.backends.pipeline.Fresco;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.smtt.sdk.QbSdk;
import com.zcx.helper.scale.ScaleScreenHelper;
import com.zcx.helper.scale.ScaleScreenHelperFactory;

import java.io.File;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MyApplication extends Application {
    public static ScaleScreenHelper scaleScreenHelper;
    protected static int mainThreadId;
    protected static Handler handler;
    protected static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RongIM.init(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
//        Fresco.initialize(this);
        preinitX5WebCore();
        //预加载x5内核
        Intent intent = new Intent(this, AdvanceLoadX5Service.class);
        startService(intent);
        scaleScreenHelper = ScaleScreenHelperFactory.create(this, 750);
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();

        File cacheFile = new File(getCacheDir(),"CacheWebView");
        CacheWebView.getWebViewCache().init(this,cacheFile,1024*1024*100,1024*1024*10);

        // 初始化
        ARouter.init(this);

//        File cacheFile = new File(this.getCacheDir(),"cache_path_name");
//        CacheWebView.getWebViewCache().init(this,cacheFile,1024*1024*100,1024*1024*10);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }


    private void preinitX5WebCore() {
        if (!QbSdk.isTbsCoreInited()) {
            QbSdk.preInit(getApplicationContext(), null);// 设置X5初始化完成的回调接口
        }
    }
    // x5 init service
    public class AdvanceLoadX5Service extends Service {
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            initX5();
        }

        private void initX5() {
            //  预加载X5内核
            QbSdk.initX5Environment(getApplicationContext(), cb);
        }

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //初始化完成回调
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };

    }
    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }



    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

//    private void connect(String token) {
//
//        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {
//
//            RongIM.connect(token, new RongIMClient.ConnectCallback() {
//
//                /**
//                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//                 */
//                @Override
//                public void onTokenIncorrect() {
//
//                }
//
//                /**
//                 * 连接融云成功
//                 * @param userid 当前 token 对应的用户 id
//                 */
//                @Override
//                public void onSuccess(String userid) {
//                    Log.d("LoginActivity", "--onSuccess" + userid);
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }
//
//                /**
//                 * 连接融云失败
//                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
//                 */
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                }
//            });
//        }
//    }

}
