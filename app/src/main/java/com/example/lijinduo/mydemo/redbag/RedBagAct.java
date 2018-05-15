package com.example.lijinduo.mydemo.redbag;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.databinding.ActRedbagBinding;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RedBagAct extends BaseActivity {

    private ActRedbagBinding binding;
    private RedBagRainMvvm viewmode;
    private Context context=RedBagAct.this;
    @Override
    public void doSmoething() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.act_redbag);
        viewmode=new RedBagRainMvvm(binding,context);
        binding.setViewModel(viewmode);
    }

    @Override
    protected void onDestroy() {
        binding.redBag.setStop(false);
        super.onDestroy();
    }
}
