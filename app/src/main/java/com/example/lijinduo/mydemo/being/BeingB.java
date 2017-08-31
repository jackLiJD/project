package com.example.lijinduo.mydemo.being;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/31
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BeingB extends BaseActivity {
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_beingb);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button3)
    public void onViewClicked() {
        startActivity(new Intent(this,BeingC.class));
    }
}
