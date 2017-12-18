package com.example.lijinduo.mydemo.tool;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;
import com.zcx.helper.scale.ScaleScreenHelper;
import com.zcx.helper.scale.ScaleScreenHelperFactory;

import java.io.File;

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
    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        Fresco.initialize(this);
        preinitX5WebCore();
        //预加载x5内核
        Intent intent = new Intent(this, AdvanceLoadX5Service.class);
        startService(intent);
        scaleScreenHelper = ScaleScreenHelperFactory.create(this, 750);
        File cacheFile = new File(getCacheDir(),"CacheWebView");
        CacheWebView.getWebViewCache().init(this,cacheFile,1024*1024*100,1024*1024*10);
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
}
