package com.example.lijinduo.mydemo.redbag;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/16
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RedBagNoGainAct extends BaseActivity {

    @BindView(R.id.redbag_nogain_bg)
    ImageView redbagNogainBg;
    @BindView(R.id.redbag_nogain_close)
    View redbagNogainClose;
    @BindView(R.id.redbag_nogain_look)
    View redbagNogainLook;
    @BindView(R.id.redbag_nogain_next)
    View redbagNogainNext;
    private Context context = RedBagNoGainAct.this;
    @Override
    public void doSmoething() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nogain_redbag);
        ButterKnife.bind(this);
        Glide.with(context).load(R.drawable.redbag_rain_null).into(redbagNogainBg);
    }

    @OnClick({R.id.redbag_nogain_close, R.id.redbag_nogain_look, R.id.redbag_nogain_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.redbag_nogain_close:
                finish();
                break;
            case R.id.redbag_nogain_look:
                Toast.makeText(context,"looklook",Toast.LENGTH_SHORT).show();
                break;
            case R.id.redbag_nogain_next:
                Toast.makeText(context,"主会场见",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
