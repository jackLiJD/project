package com.example.lijinduo.mydemo.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/9/5
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Book extends BaseActivity {
    @BindView(R.id.book_web)
    WebView bookWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        ButterKnife.bind(this);
        bookWeb.loadUrl("https://www.baidu.com/");
    }

    @OnClick(R.id.book_web)
    public void onViewClicked() {
    }
}
