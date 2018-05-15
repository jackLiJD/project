package com.example.lijinduo.mydemo.rxjava;

import com.example.lijinduo.mydemo.retrofit.NetError;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/14
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public  abstract class NetCallBack<T> implements Observer<T>{
     abstract void onSuccess(T value);

     abstract void onFail(Throwable e);

     abstract void done();

     abstract void onMySubscribe(Disposable d);


    public NetCallBack() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onMySubscribe(d);
    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {
        done();
    }

}
