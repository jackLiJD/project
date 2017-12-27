package com.example.lijinduo.mydemo.adaptation;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.NoScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/25
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class AdaptationAct extends BaseActivity {
    @BindView(R.id.out_scroll)
    NoScrollView outScroll;
    @BindView(R.id.crop)
    LinearLayout crop;
    int height = 0;
    @BindView(R.id.outview)
    RelativeLayout outview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adaptation);
        ButterKnife.bind(this);
        outScroll.setCallback(new NoScrollView.spaceY() {
            @Override
            public void space(int y) {
                height+=y;
                if (height < 0) {
                    height = 0;
                }
                if (height >outview.getMeasuredHeight()-crop.getMeasuredHeight()) {
                    height=outview.getMeasuredHeight()-crop.getMeasuredHeight();
                }
                crop.setTranslationY(height);
            }
        });
    }


}
