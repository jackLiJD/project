package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/27
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class  CacheWebView extends WebView {

    private static final String CACHE_NAME = "CacheWebView";
    private static final int CACHE_SIZE = 200*1024*1024;

    private String mAppCachePath = "";

    private CacheWebViewClient mCacheWebViewClient;

    private HashMap<String,Map> mHeaderMaps;
    private Vector<String> mVectorUrl = null;

    private String mUserAgent="";


    public CacheWebView(Context context) {
        this(context,null,0);
    }

    public CacheWebView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CacheWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        initData();
        initSettings();
        initWebViewClient();
    }

    private void initData() {
        mHeaderMaps = new HashMap<>();
        mVectorUrl = new Vector<>();

        File cacheFile = new File(getContext().getCacheDir(),CACHE_NAME);
        try {
               getWebViewCache().openCache(getContext(),cacheFile,CACHE_SIZE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getOriginUrl(){
        String ou = "";
        try {
            ou =  mVectorUrl.lastElement();
            URL url = new URL(ou);
            int port = url.getPort();
            ou=  url.getProtocol()+"://"+url.getHost()+(port==-1?"":":"+port);
        }catch (Exception e){
        }
        return ou;
    }
    public String getRefererUrl(){
        try {
            return mVectorUrl.get(mVectorUrl.size()-2);
        }catch (Exception e){
        }
        return "";
    }
    public static WebViewCache getWebViewCache(){
        return WebViewCache.getInstance();
    }

    public void setWebViewClient(WebViewClient client){
        mCacheWebViewClient.setCustomWebViewClient(client);
    }

    private void initWebViewClient() {
        mCacheWebViewClient = new CacheWebViewClient();
        super.setWebViewClient(mCacheWebViewClient);
    }

    public void setCacheStrategy(WebViewCache.CacheStrategy cacheStrategy){
        mCacheWebViewClient.setCacheStrategy(cacheStrategy);
    }

    public static CacheWebView cacheWebView(Context context){
        return new CacheWebView(context);
    }

    public void setEnableCache(boolean enableCache){
        mCacheWebViewClient.setEnableCache(enableCache);
    }
    public void loadUrl(String url){
        if (!mVectorUrl.contains(url)){
            mVectorUrl.add(url);
        }
        super.loadUrl(url);

    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (!mVectorUrl.contains(url)){
            mVectorUrl.add(url);
        }
        mHeaderMaps.put(url,additionalHttpHeaders);
        getWebViewCache().addHeaderMap(url,additionalHttpHeaders);
        super.loadUrl(url,additionalHttpHeaders);
    }
    public void setBlockNetworkImage(boolean isBlock){
        mCacheWebViewClient.setBlockNetworkImage(isBlock);
    }

    @Override
    public void goBack() {

        try {
            if (!mVectorUrl.isEmpty()){
                mVectorUrl.remove(mVectorUrl.size()-1);
            }
        }catch (Exception e){
        }
        super.goBack();
    }

    private void initSettings(){
        WebSettings webSettings = this.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        webSettings.setDefaultTextEncodingName("UTF-8");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(this,true);
        }
        if (NetworkUtils.isConnected(this.getContext()) ){
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(
//                   2);
//        }

        mUserAgent = webSettings.getUserAgentString();
        setCachePath();

    }
    public String getUserAgent(){
        return mUserAgent;
    }

    public void setUserAgent(String userAgent){
        mUserAgent = userAgent;
        WebSettings webSettings = this.getSettings();
        webSettings.setUserAgentString(userAgent);
    }

    private void setCachePath(){

        File  cacheFile = new File(this.getContext().getCacheDir(),CACHE_NAME);
        String path = cacheFile.getAbsolutePath();
        mAppCachePath = path;

        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }

        WebSettings webSettings = this.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(path);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(path);
    }
    public String getAppCachePath(){
        return mAppCachePath;
    }

    public void clearCache(){

        mVectorUrl.clear();

        FileUtil.deleteDirs(mAppCachePath,false);
        getWebViewCache().clean();
    }

    public void destroy(){

        mVectorUrl.clear();
        mVectorUrl = null;

        getWebViewCache().clearHeaderMap(mHeaderMaps);
        mHeaderMaps.clear();
        mHeaderMaps = null;

        ViewParent viewParent = this.getParent();
        if (viewParent == null){
            super.destroy();
            return ;
        }
        ViewGroup parent = (ViewGroup) viewParent;
        parent.removeView(this);
        super.destroy();
    }
    public void evaluateJS(String strJsFunction){
        this.evaluateJS(strJsFunction,null);
    }
    public void evaluateJS(String strJsFunction,ValueCallback valueCallback){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&valueCallback!=null) {
            this.evaluateJavascript("javascript:"+strJsFunction, valueCallback);
        } else {
            this.loadUrl("javascript:"+strJsFunction);
        }
    }

}
