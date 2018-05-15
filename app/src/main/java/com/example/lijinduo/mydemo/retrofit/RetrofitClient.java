package com.example.lijinduo.mydemo.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RetrofitClient {

    private int TIME_OUT=20;

    private int READ_OUT=20;

    // retrofit实例
    private Retrofit retrofit;

    public static String cookies="";

//    public static String baseUrl="http://tj.nineton.cn/?";



    public static String baseUrl="https://test1rest.edspay.com/";
    public RetrofitClient() {
        updataRetrofit();
    }

    /**
     * 单例对象
     */
    private static RetrofitClient instance;


    /**
     * @return
     * 获取对象
     */
    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class){
            if (instance == null)
                instance = new RetrofitClient();
            }
        }

        return instance;
    }

    public void updataRetrofit() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_OUT, TimeUnit.SECONDS);
        // 添加签名参数
        builder.addInterceptor(BasicParamsInject.getInstance().getInterceptor());
        // 打印参数
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        if(!cookies.equals("")) {
            builder.addInterceptor((new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Cookie", cookies)
                            .build();
                    return chain.proceed(request);
                }

            }));
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        getServiceMap().clear();
    }

    private static TreeMap<String, Object> serviceMap;

    private static TreeMap<String, Object> getServiceMap() {
        if (serviceMap == null)
            serviceMap = new TreeMap<>();
        return serviceMap;
    }
    public static <T> T getService(Class<T> clazz) {
        if (getServiceMap().containsKey(clazz.getSimpleName())) {
            return (T) getServiceMap().get(clazz.getSimpleName());
        }

        T service = RetrofitClient.getInstance().retrofit.create(clazz);
        getServiceMap().put(clazz.getSimpleName(), service);
        return service;
    }
}
