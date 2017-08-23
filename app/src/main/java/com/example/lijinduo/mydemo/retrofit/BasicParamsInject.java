package com.example.lijinduo.mydemo.retrofit;

import okhttp3.Interceptor;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BasicParamsInject {
    private static final String CHARSET_NAME = "UTF-8";
    private BasicParamsInterceptor interceptor;

    private BasicParamsInject() {
    }

    public static BasicParamsInject getInstance() {
        return new BasicParamsInject();
    }

    public Interceptor getInterceptor() {
                if (interceptor == null) {
                    upDataInterceptor();
        }
        return interceptor;
    }

    public void upDataInterceptor() {


        interceptor = new BasicParamsInterceptor.Builder()
                //公共key和value
                .addParam("keys", "values")
                .build();

    }


}