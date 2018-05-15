package com.example.lijinduo.mydemo.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RxJavaMyTestAct extends BaseActivity {
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.rxjava_stop)
    TextView rxjavaStop;

    private Disposable disposable;

    @Override
    public void doSmoething() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rxjava_mytest);
        ButterKnife.bind(this);
        initCountDown();
    }

    /**
     * 倒计时
     */
    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(60)
                .map(new Function<Long, Object>() {
                    @Override
                    public Object apply(Long aLong) throws Exception {
                        return aLong;
                    }
                }).compose(RxHelper.rxSchedulerHelper()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Object value) {
                textView3.setText(value + "");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.textView3, R.id.rxjava_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView3:
//        startActivity(new Intent(this, FlowableAct.class));
                break;
            case R.id.rxjava_stop:
                disposable.dispose();
                break;
        }
    }
}
