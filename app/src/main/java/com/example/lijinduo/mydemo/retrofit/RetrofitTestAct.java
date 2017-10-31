package com.example.lijinduo.mydemo.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.CommonPopWindow;

import retrofit2.Call;
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
public class RetrofitTestAct extends BaseActivity {
    Button query_weather,other_load,system_maintain;
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
//        Call<InvestListBean> call = RetrofitClient.getInstance().getService(IWeather.class).invest();
//        call.enqueue(new RequestCallBack<InvestListBean>(false) {
//            @Override
//            public void onSuccess(Call<InvestListBean> call, Response<InvestListBean> response) {
//                InvestListBean investBean = response.body();
//                query_weather.setText(investBean.getResData().getList().get(0).getGoodType());
//            }
//        });
//        Intent intent=new Intent(RetrofitTestAct.this,DoubleActTest.class);
//        startActivity(intent);

    }


}
