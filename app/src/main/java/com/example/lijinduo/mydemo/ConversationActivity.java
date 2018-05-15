package com.example.lijinduo.mydemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ConversationActivity extends FragmentActivity {

    @BindView(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        ButterKnife.bind(this);
        String name = getIntent().getData().getQueryParameter("title");
        if (!TextUtils.isEmpty(name)) {
            title.setText(name);
        }

    }

}