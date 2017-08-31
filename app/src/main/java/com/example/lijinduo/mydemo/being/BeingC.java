package com.example.lijinduo.mydemo.being;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/31
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BeingC extends BaseActivity {
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_beingc);
        ButterKnife.bind(this);
        if (AppManager.getAppManager().isBeing(BeingB.class)) {
            textView.setText("B存在");
        }else{
            textView.setText("B不存在");
        }

    }
}
