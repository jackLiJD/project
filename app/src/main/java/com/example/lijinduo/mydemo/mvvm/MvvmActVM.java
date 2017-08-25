package com.example.lijinduo.mydemo.mvvm;

import android.app.ActivityManager;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.util.Log;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BR;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.retrofit.IWeather;
import com.example.lijinduo.mydemo.retrofit.InvestListBean;
import com.example.lijinduo.mydemo.retrofit.RequestCallBack;
import com.example.lijinduo.mydemo.retrofit.RetrofitClient;
import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.AppTool;
import com.example.lijinduo.mydemo.tool.Constant;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MvvmActVM extends BaseRecyclerViewVM{
//    public final ObservableList items = new ObservableArrayList<>();
    public  ObservableField<InvestListBean> investListBeanObservableField = new ObservableField<>();
    public MvvmActVM() {
        init();
    }

    private void init() {
        Constant.OTHERLOAD=true;
        Call<InvestListBean> call = RetrofitClient.getInstance().getService(IWeather.class).invest();
        call.enqueue(new RequestCallBack<InvestListBean>(false) {
            @Override
            public void onSuccess(Call<InvestListBean> call, Response<InvestListBean> response) {
                investListBeanObservableField.set(response.body());
//                Toast.makeText(AppManager.getAppManager().currentActivity(), response.body().getResData().getList().get(0).getGoodType(), Toast.LENGTH_SHORT).show();
                ArrayList<InvestListBean.ResDataBean.Bean> beans= (ArrayList<InvestListBean.ResDataBean.Bean>) response.body().getResData().getList();
                AppTool.log("撒打算的",response.body().getResData().getList().get(0).getGoodType());
                items.addAll(beans);
                Constant.OTHERLOAD=false;

            }
        });

    }

    @Override
    protected void selectView(ItemView itemView, int position, Object item) {
        itemView.set(BR.item, R.layout.item_mvvm);

    }
}
