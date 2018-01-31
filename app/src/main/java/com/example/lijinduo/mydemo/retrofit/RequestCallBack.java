package com.example.lijinduo.mydemo.retrofit;

import com.example.lijinduo.mydemo.tool.AppTool;
import com.example.lijinduo.mydemo.tool.Constant;
import com.example.lijinduo.mydemo.view.CommonPopWindow;

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
 * 参考链接：
 */
public abstract class RequestCallBack<T> implements Callback<T> {
    public abstract void onSuccess(Call<T> call, Response<T> response);

    private boolean isShow = false;

    public RequestCallBack(boolean isShow) {
        this.isShow = isShow;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        AppTool.log("onResponseFather", "onResponse" + ((HttpResult) response.body()).getResCode());
//        if (!Constant.OTHERLOAD) {
//            Constant.OTHERLOAD = !Constant.OTHERLOAD;
//            CommonPopWindow commonPopWindow = new CommonPopWindow("该帐号已经被他人顶掉", new FatherInterFace() {
//                @Override
//                public void data() {
//                }
//            });
//        }
        if (response.isSuccessful()) {
            onSuccess(call, response);
        }
    }

    //网络请求失败和异常会执行onfailure
    @Override
    public void onFailure(Call<T> call, Throwable t) {

        AppTool.log("onFailureFather", "onFailure" + t.getMessage());
    }
}