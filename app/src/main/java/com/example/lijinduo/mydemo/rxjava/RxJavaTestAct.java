package com.example.lijinduo.mydemo.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.retrofit.IWeather;
import com.example.lijinduo.mydemo.retrofit.RequestCallBack;
import com.example.lijinduo.mydemo.retrofit.RetrofitClient;
import com.example.lijinduo.mydemo.tool.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/2
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 * https://www.zhihu.com/question/53151203
 * https://github.com/ReactiveX/RxJava
 * http://gank.io/post/560e15be2dca930e00da1083#toc_1
 * https://www.jianshu.com/p/464fa025229e
 */

//observable  美  [əb'zɝvəbl] 被观察者
//Observer    美  [əb'zɝvɚ]
//subscribe   美  [səb'skraɪb]
//Subscriber
public class RxJavaTestAct extends BaseActivity {

    @BindView(R.id.rxjava_img)
    ImageView rxjavaImg;
    @BindView(R.id.textView2)
    Button textView2;
    @BindView(R.id.textView100)
    Button textView100;
    @BindView(R.id.textView1001)
    Button textView1001;
    @BindView(R.id.textView1002)
    Button textView1002;
    @BindView(R.id.myedittextview)
    EditText myedittextview;
    private String TAG = "rxJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rxjava_test);
        ButterKnife.bind(this);
        rxTest();
        rxJavaMap();
        flatMap();
        concatMap();
        zipRxJavaTest();
        RxView.clicks(textView1002).throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                rxjavaandretfic();
                rxjavaandretficCall();

            }
        });

    }


    private void rxTest() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
                emitter.onNext(3);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
//                d.dispose();
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "rxTest" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
    }

    private void rxjavaandretfic() {
        RetrofitClient.getInstance().getService(IWeather.class).weather("CHSH000000")
                .compose(RxHelper.<TianQiNewBean>rxSchedulerHelper())
                .subscribe(new NetCallBack<TianQiNewBean>() {


                    @Override
                    void onSuccess(TianQiNewBean value) {
                        Log.d(TAG, "onsuccess: " + value.getHourly().get(0).getText());
                    }

                    @Override
                    void onFail(Throwable e) {
                        Log.d(TAG, "onFail: " + e.getMessage());
                    }

                    @Override
                    void done() {
                        Log.d(TAG, "done");

                    }

                    @Override
                    void onMySubscribe(Disposable d) {
                        Log.d(TAG, "onMySubscribe");
                    }
                });

    }

    private void rxjavaandretficCall() {
        Call<TianQiNewBean> call = RetrofitClient.getInstance().getService(IWeather.class).weatherCall("CHSH000000");
        call.enqueue(new RequestCallBack<TianQiNewBean>(false) {
            @Override
            public void onSuccess(Call<TianQiNewBean> call, Response<TianQiNewBean> response) {
                Log.d(TAG, "onSuccess: Call");
                response.code();
            }

            @Override
            public void onFailure(Call<TianQiNewBean> call, Throwable t) {
                Log.d(TAG, "onFailure: Call" + t.getMessage());
            }

            @Override
            public void onResponse(Call<TianQiNewBean> call, Response<TianQiNewBean> response) {
                super.onResponse(call, response);
                Log.d(TAG, "onResponse: Call");
            }
        });


    }


//    private void rxjavaandretfic() {
//        RetrofitClient.getInstance().getService(IWeather.class).weather("CHSH000000").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TianQiNewBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(TianQiNewBean value) {
//                Log.d(TAG, "onNext:两秒内只生效一次 " + value.getStatus() + value.getHourly().get(3).getText());
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//
//    }


    /**
     * map类型转换 integer转换String
     */
    private void rxJavaMap() {
        Observable.just(1, 2, 3).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "this is str=" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.d(TAG, "accept:maptest= " + o);
            }
        });
    }


    /**
     * flatMap
     * flatMap是一个非常强大的操作符, 先用一个比较难懂的概念说明一下:
     * FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
     */
    private void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "flatmap" + s);
            }
        });


    }

    /**
     * 功能同flatmap 只是是有序的 上游顺序保持到下游顺序
     */
    private void concatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "concatMap" + s);
            }
        });

    }

    private void zipRxJavaTest() {

        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, Object>() {
            @Override
            public Object apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Log.d("rxJavaZip", "this is zip" + value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 2个网络请求zip
     */
    private void zipRxJavaRetfic() {

        Observable<TianQiNewBean> observable1 = RetrofitClient.getInstance().getService(IWeather.class).weather("CHSH000000").subscribeOn(Schedulers.io());

        Observable<TianQiNewBean> observable2 = RetrofitClient.getInstance().getService(IWeather.class).weather("CHSH000000").subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<TianQiNewBean, TianQiNewBean, Object>() {
            @Override
            public Object apply(TianQiNewBean tianQiNewBean, TianQiNewBean tianQiNewBean2) throws Exception {
                return tianQiNewBean.getHourly().get(0).getText() + "|" + tianQiNewBean.getHourly().get(1).getText();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, "accept: 为什么不崩溃呢");
                textView1001.setText("" + o);
                rxjavaImg.setImageResource(R.drawable.money_baobao);
            }
        });

    }

    private void rxJavaFlowable() {


    }


    @Override
    public void doSmoething() {

    }


    @OnClick({R.id.textView2, R.id.textView100, R.id.textView1001})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView2:
                zipRxJavaRetfic();
                startActivity(new Intent(this, FlowableAct.class));
                break;
            case R.id.textView100:
                startActivity(new Intent(this, RxJavaMyTestAct.class));
                break;
            case R.id.textView1001:
                zipRxJavaRetfic();
                finish();
                break;
        }
    }
}
