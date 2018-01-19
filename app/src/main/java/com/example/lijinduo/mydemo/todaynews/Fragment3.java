package com.example.lijinduo.mydemo.todaynews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/8
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Fragment3 extends Fragment {

    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.btnlin)
    LinearLayout btnlin;
    Unbinder unbinder;
    private String title;

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act_frag3, container, false);
        Bundle mBundle = getArguments();
        String title = mBundle.getString("string");
        unbinder = ButterKnife.bind(this, view);
        button12.setText(title);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
