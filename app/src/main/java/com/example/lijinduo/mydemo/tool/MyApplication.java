package com.example.lijinduo.mydemo.tool;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.zcx.helper.scale.ScaleScreenHelper;
import com.zcx.helper.scale.ScaleScreenHelperFactory;

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
        scaleScreenHelper = ScaleScreenHelperFactory.create(this, 750);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}
