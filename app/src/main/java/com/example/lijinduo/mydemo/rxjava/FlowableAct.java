package com.example.lijinduo.mydemo.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class FlowableAct extends BaseActivity {
    @BindView(R.id.button16)
    Button button16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flowable);
        ButterKnife.bind(this);
        flowableTest1();
        flowableTest2();
    }


    /**
     * 如果观察者与被观察者在同一线程中
     * 如果没有设置请求的个数会跑出异常
     */
    private void flowableTest1() {
        final String TAG = "flowableTest1";
        //上游 被观察者
        Flowable<String> upStream = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                Log.d(TAG, "current requested: " + e.requested());
                Log.d(TAG, "subscribe: a");
                e.onNext("a");
                Log.d(TAG, "subscribe: b");
                e.onNext("b");
                Log.d(TAG, "subscribe: c");
                e.onNext("c");
                Log.d(TAG, "subscribe: d");
                e.onNext("d");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        //下游观察者
        Subscriber<String> downStream = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
//                s.cancel(); 相当于d.dispose()；
                s.request(4);

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        upStream.subscribe(downStream);
    }


    /**
     * 如果观察者与被观察者在不同的线程
     * 如果下流／观察者 没有设定请求的个数  上游会发送 但是不会下流相应
     * 会自动存储128个对象
     * BackpressureStrategy.BUFFER 增加存储量1千个对象 BUFFER缓冲区
     * BackpressureStrategy.DROP 把不用的对象去掉  drop 剪切
     * BackpressureStrategy.LATEST 最新的
     */
    private void flowableTest2() {
        final String TAG = "flowableTest2";
        //上游 被观察者
        Flowable<String> upStream = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("a");
                e.onNext("b");
                e.onNext("c");
                e.onNext("d");
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //下游观察者
        Subscriber<String> downStream = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
//                s.cancel(); 相当于d.dispose()；
//                s.request(3);
                subscription=s;

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        upStream.subscribe(downStream);


    }


    @Override
    public void doSmoething() {

    }

    Subscription subscription;
    @OnClick(R.id.button16)
    public void onViewClicked() {
        subscription.request(129);
    }
}
