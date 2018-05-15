package com.example.lijinduo.mydemo.redbag;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
public class RedBagGainAct extends BaseActivity {
    @BindView(R.id.redbag_gain_close)
    View redbagGainClose;
    @BindView(R.id.redbag_gain_top)
    RelativeLayout redbagGainTop;
    @BindView(R.id.redbag_nogain_next)
    View redbagNogainNext;
    @BindView(R.id.redbag_gain)
    ImageView redbagGain;
    private Context context = RedBagGainAct.this;
    @Override
    public void doSmoething() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gain_redbag);
        ButterKnife.bind(this);
        int num = getIntent().getIntExtra("number", 0);
        switch (num) {
            case 1:
                redbagGainTop.setBackground(ContextCompat.getDrawable(context, R.drawable.red_gain_top));
                break;
            case 2:
                redbagGainTop.setBackground(ContextCompat.getDrawable(context, R.drawable.red_gain_top2));
                break;
            case 3:
                redbagGainTop.setBackground(ContextCompat.getDrawable(context, R.drawable.red_gain_top3));
                break;
        }

        Glide.with(context).load(R.drawable.redbag_rain_null).into(redbagGain);

    }

    @OnClick({R.id.redbag_gain_close, R.id.redbag_nogain_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.redbag_gain_close:
                finish();
                break;
            case R.id.redbag_nogain_next:
                Toast.makeText(RedBagGainAct.this, "进入主会场", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
