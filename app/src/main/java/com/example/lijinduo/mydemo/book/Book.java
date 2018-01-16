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
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.redbag.RedBagAct;
import com.example.lijinduo.mydemo.view.CacheWebView;
import com.example.lijinduo.mydemo.view.WebViewCache;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
//import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
//import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.webview_contentChacheall)
    FrameLayout webviewContentChacheall;
    @BindView(R.id.reload)
    Button reload;
    private String TAG = "TAG";
    //是否缓存
    private String isCache = null;
    private Handler handler = new Handler();
    private Context context = Book.this;
    private String commonUrl;
    private WebView bookWeb;
    private CacheWebView cacheweb;
    private ren.yale.android.cachewebviewlib.CacheWebView cacheWebView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_book);
        isCache = getIntent().getStringExtra("isCache");
        commonUrl = getIntent().getStringExtra("commonUrl");
//        commonUrl = "file:///android_asset/web1.html";
        commonUrl="https://test2static.edspay.com/#/wx/iceSnowCarnival?uid=300338&token=1515999065926300338&vcode=3.2.0&osType=Android&osVersion=Android:4.4.2&zhou=1515999189234";

        ButterKnife.bind(this);
        bookWeb = new WebView(context);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book.this, RedBagAct.class));
            }
        });
        webviewContent.addView(bookWeb, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));


        cacheweb = new CacheWebView(context);
        webviewContentChache.addView(cacheweb, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
//        cacheweb.setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
//        cacheweb.getWebViewCache().getStaticRes().addExtension("swf").addExtension("svg")
//                .addRamExtension("png").addRamExtension("html");

        cacheWebView = new ren.yale.android.cachewebviewlib.CacheWebView(context);
        webviewContentChacheall.addView(cacheWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));


        cacheWebView.setCacheStrategy(ren.yale.android.cachewebviewlib.WebViewCache.CacheStrategy.FORCE);
        cacheWebView.getWebViewCache().getStaticRes().addExtension("swf").addExtension("svg")
                .addRamExtension("png").addRamExtension("html").removeExtension("js");

//        commonUrl = "https://ywww.edspay.com/#/channel/ypwk";
        //        bookWeb.loadUrl("https://www.baidu.com/");
//        bookWeb.loadUrl("https://test1static.edspay.com/#/wxAnnouncement?id=401&uid=&token=&vcode=3.0.4&osType=Android&osVersion=Android:6.0");
        initView();


    }


    private void initView() {
        WebSettings settings = cacheWebView.getSettings();
        //设置背景需要代码证先设置颜色在设置背景图
//        bookWeb.setBackgroundColor(0);
//        bookWeb.setBackgroundResource(0);
        //不加这句话vue框架的不好使
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        cacheweb.setWebViewClient(new WebClient());
//        cacheWebView.setDownloadListener(new MyWebViewDownLoadListener());
        cacheweb.addJavascriptInterface(new WebReturn(), "webReturn");
        cacheweb.setWebChromeClient(new WebChromeClient());
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

//        @Override
//        public void onPageFinished(WebView view, String url) {
//            Log.d(TAG, "onPageFinished: " + url);
//            //防止webview销毁 执行该方法而引发的空指针异常
//            if (bookWeb != null) {
//                bookWeb.loadUrl("javascript:appReturnInfo(" + "传递前段信息" + ")");
//            }
//            super.onPageFinished(view, url);
//        }
//
//        @Override
//        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
//            Log.d(TAG, "onPageStarted: " + s);
//            super.onPageStarted(webView, s, bitmap);
//
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            Log.d(TAG, "shouldOverrideUrlLoading: " + url);
//            if (url.startsWith("http") || url.startsWith("https")) {
//                view.loadUrl(url);
//            } else {
//                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(in);
//            }
//            return true;
//        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return true;
        }

//        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }


//        @Override
//        public void onReceivedError(android.webkit.WebView view, int errorCode, String description, String failingUrl) {
//            super.onReceivedError(view, errorCode, description, failingUrl);
//            Toast.makeText(Book.this,"2",200).show();
//        }
//
//        @Override
//        public void onReceivedHttpError(android.webkit.WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//            super.onReceivedHttpError(view, request, errorResponse);
//            Toast.makeText(Book.this,"3",200).show();
//        }
//
//        @Override
//        public void onReceivedError(android.webkit.WebView view, WebResourceRequest request, WebResourceError error) {
//            super.onReceivedError(view, request, error);
//            Toast.makeText(Book.this,"1",200).show();
//        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }


//        @Override
//        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//            sslErrorHandler.proceed();  // 接受所有网站的证书
//            super.onReceivedSslError(webView, sslErrorHandler, sslError);
//        }
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
                cacheWebView.loadUrl(commonUrl);
            } else {
//                bookWeb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
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
//    @Override
//    protected void onDestroy() {
//        if (bookWeb != null) {
//            bookWeb.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            bookWeb.clearHistory();
//            ((ViewGroup) webviewContent.getParent()).removeView(bookWeb);
//            bookWeb.destroy();
//            bookWeb = null;
//        }
//        super.onDestroy();
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        commonUrl="https://www.baidu.com/";
//        bookWeb.loadUrl(commonUrl);
//    }
}
