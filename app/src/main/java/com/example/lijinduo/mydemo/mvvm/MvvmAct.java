package com.example.lijinduo.mydemo.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.databinding.ActMvvmBinding;
import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.MyApplication;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MvvmAct extends BaseActivity{
    private ActMvvmBinding binding;
    private MvvmActVM viewmode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.act_mvvm);
        viewmode=new MvvmActVM(MvvmAct.this);
        binding.setViewModel(viewmode);
    }
    @Override
    public void doSmoething() {

    }
}
