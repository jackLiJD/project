package com.example.lijinduo.mydemo.retrofit;


import com.example.lijinduo.mydemo.rxjava.TianQiNewBean;
import com.example.lijinduo.mydemo.ry.RongYunBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @GET("Heart/index/future24h/")
    Observable<TianQiNewBean> weather(@Query("city")String location);

    @GET("Heart/index/future24h/")
    Call<TianQiNewBean> weatherCall(@Query("city")String location);

    @GET("invest/investList.html")
    Call<String> invest();

    @FormUrlEncoded
    @POST("user/getToken.json")
    Observable<RongYunBean> getToken(@Field("userId") String userId, @Field("name") String name, @Field("portraitUri") String portraitUri);

    /**
     * 强制更新
     */
    @FormUrlEncoded
    @POST("aaaapp/version/update")
    Call<String> upDate(@Field("channel") int channelCode, @Field("version") String version);





}
