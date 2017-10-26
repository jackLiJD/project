package com.example.lijinduo.mydemo.book;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.webview_content)
    FrameLayout webviewContent;
    @BindView(R.id.webview_contentChache)
    FrameLayout webviewContentChache;
    private String TAG = "TAg";
    //是否缓存
    private String isCache = null;
    private Handler handler = new Handler();
    private Context context = Book.this;
    private String commonUrl;
    private WebView bookWeb;
    private CacheWebView cacheweb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        isCache = getIntent().getStringExtra("isCache");
        commonUrl = getIntent().getStringExtra("commonUrl");
        commonUrl = "https://test1static.edspay.com/#/storeIndex?uid=&token=&vcode=3.0.4&osType=Android&osVersion=Android:6.0";
        ButterKnife.bind(this);
        bookWeb = new WebView(context);
        webviewContent.addView(bookWeb, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        cacheweb = new CacheWebView(context);
        webviewContentChache.addView(cacheweb, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        cacheweb.setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
        cacheweb.getWebViewCache().getStaticRes().addExtension("swf").removeExtension("svg")
                .addRamExtension("png").removeRamExtension("html");



//        commonUrl = "https://ywww.edspay.com/#/channel/ypwk";
        //        bookWeb.loadUrl("https://www.baidu.com/");
//        bookWeb.loadUrl("https://test1static.edspay.com/#/wxAnnouncement?id=401&uid=&token=&vcode=3.0.4&osType=Android&osVersion=Android:6.0");
        initView();
    }


    private void initView() {
        WebSettings settings = bookWeb.getSettings();
        //设置背景需要代码证先设置颜色在设置背景图
//        bookWeb.setBackgroundColor(0);
//        bookWeb.setBackgroundResource(0);
        //不加这句话vue框架的不好使
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        bookWeb.setWebViewClient(new WebClient());
        bookWeb.setDownloadListener(new MyWebViewDownLoadListener());
        bookWeb.addJavascriptInterface(new WebReturn(), "webReturn");
        bookWeb.setWebChromeClient(new WebChromeClient());
        CaChe();
    }

    //js
    class WebReturn {
        //注解
        @JavascriptInterface
        public void closeWebView() {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    finish();
                }
            });
        }
    }


    private class WebChromeClient extends com.tencent.smtt.sdk.WebChromeClient {
        //动态更改标题
        @Override
        public void onReceivedTitle(WebView webView, String s) {
            Log.d(TAG, "onReceivedTitle: " + s);
            super.onReceivedTitle(webView, s);

        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            Log.d(TAG, "onProgressChanged: 网站进度" + i);
        }
    }


    //webclient
    public class WebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished: " + url);
            //防止webview销毁 执行该方法而引发的空指针异常
            if (bookWeb != null) {
                bookWeb.loadUrl("javascript:appReturnInfo(" + "传递前段信息" + ")");
            }
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            Log.d(TAG, "onPageStarted: " + s);
            super.onPageStarted(webView, s, bitmap);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading: " + url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();  // 接受所有网站的证书
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    //下载
    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    //缓存
    private void CaChe() {
        isCache = "不等于null";
        if (isCache != null) {
            if (isNetworkConnected()) {
                bookWeb.loadUrl(commonUrl);
                cacheweb.loadUrl(commonUrl);
            } else {
                bookWeb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                bookWeb.getSettings().setCacheMode(android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
                // 开启 DOM storage API 功能
                bookWeb.getSettings().setDomStorageEnabled(true);
                //开启 database storage API 功能
                bookWeb.getSettings().setDatabaseEnabled(true);
                String cacheDirPath = getFilesDir().getAbsolutePath() + "edspay_Chache";
                //设置数据库缓存路径
                bookWeb.getSettings().setDatabasePath(cacheDirPath);
                //设置  Application Caches 缓存目录
                bookWeb.getSettings().setAppCachePath(cacheDirPath);
                //开启 Application Caches 功能
                bookWeb.getSettings().setAppCacheEnabled(true);
                //是否存在缓存功能
                bookWeb.loadUrl(commonUrl);
            }
        } else {
            if (isNetworkConnected()) {
                bookWeb.loadUrl(commonUrl);
            } else {
                Toast.makeText(context, "-加载无网图-", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (bookWeb.canGoBack()) {
            bookWeb.goBack();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    // 网络状态
    public boolean isNetworkConnected() {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空
    @Override
    protected void onDestroy() {
        if (bookWeb != null) {
            bookWeb.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            bookWeb.clearHistory();
            ((ViewGroup) webviewContent.getParent()).removeView(bookWeb);
            bookWeb.destroy();
            bookWeb = null;
        }
        super.onDestroy();
    }
}
