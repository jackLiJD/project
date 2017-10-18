package com.example.lijinduo.mydemo.book;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.yale.android.cachewebviewlib.CacheWebView;
import ren.yale.android.cachewebviewlib.WebViewCache;

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
    CacheWebView bookWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        ButterKnife.bind(this);
        bookWeb.setWebViewClient(new WebClient());
        bookWeb.setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
        bookWeb.setEnableCache(true);
        bookWeb.getWebViewCache().getStaticRes().addRamExtension("png").addRamExtension("html");
        bookWeb.loadUrl("https://test1static.edspay.com/#/wxRankingList");
//        bookWeb.loadUrl("https://www.baidu.com/");
    }

    public class WebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();  // 接受所有网站的证书
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (bookWeb.canGoBack()) {
            bookWeb.goBack();
            return false;
        }else{
            return super.onKeyDown(keyCode, event);
        }

    }

    @OnClick(R.id.book_web)
    public void onViewClicked() {
    }
}
