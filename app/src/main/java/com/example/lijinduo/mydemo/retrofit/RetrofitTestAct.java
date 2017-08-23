package com.example.lijinduo.mydemo.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：http://www.jianshu.com/p/eab096d82ce1
 */
public class RetrofitTestAct extends Activity {
    Button query_weather;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_retrofit);
        query_weather = (Button) findViewById(R.id.query_weather);
        query_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }

    private void init() {
        Call<TianQiBean> call = RetrofitClient.getInstance().getService(IWeather.class).weather("rot2enzrehaztkdk", "beijing");
        call.enqueue(new Callback<TianQiBean>() {
            @Override
            public void onResponse(Call<TianQiBean> call, Response<TianQiBean> response) {
                TianQiBean tianqiBean = response.body();
                Log.d("cylog", tianqiBean.getResults().get(0).getNow().getTemperature() + "");
                query_weather.setText("北京温度"+ tianqiBean.getResults().get(0).getNow().getTemperature() + "");
            }

            @Override
            public void onFailure(Call<TianQiBean> call, Throwable t) {
                Log.d("cylog", "Error" + t.toString());
            }
        });


    }


}
