package com.example.lijinduo.mydemo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public interface IWeather {
    @GET("/v3/weather/now.json")
    Call<TianQiBean> weather(@Query("key")String key, @Query("location")String location);

}
