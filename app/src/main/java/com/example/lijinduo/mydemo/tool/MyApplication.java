package com.example.lijinduo.mydemo.tool;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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
        File cacheFile = new File(this.getCacheDir(),"cache_path_name");
        CacheWebView.getWebViewCache().init(this,cacheFile,1024*1024*100,1024*1024*10);//100M 磁盘缓存空间,10M 内存缓存空间
        scaleScreenHelper = ScaleScreenHelperFactory.create(this, 750);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}
