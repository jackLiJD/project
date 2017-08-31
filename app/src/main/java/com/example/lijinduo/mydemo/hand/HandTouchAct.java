package com.example.lijinduo.mydemo.hand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.AppTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/30
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class HandTouchAct extends BaseActivity {
    @BindView(R.id.in_lin)
    InLin inLin;
    @BindView(R.id.middle_lin)
    MiddleLin middleLin;
    @BindView(R.id.out_lin)
    OutLin outLin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_handtouch);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.in_lin, R.id.middle_lin, R.id.out_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.in_lin:
                AppTool.log("in");
                break;
            case R.id.middle_lin:
                AppTool.log("middle");
                break;
            case R.id.out_lin:
                AppTool.log("out");
                break;
        }
    }
}
