package com.example.lijinduo.mydemo.electrocardiogram;

import android.os.Bundle;
import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/2
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ElectrocardiogramAct extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_electrocardiogram);
        ButterKnife.bind(this);
    }


}
