package com.myp.myapplication.h5;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myp.myapplication.R;
import com.myp.myapplication.api.AppConfig;
import com.myp.myapplication.mvp.MVPBaseActivity;
import com.myp.myapplication.my;

import java.util.HashMap;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class H5Activity extends MVPBaseActivity<H5Contract.View, H5Presenter> implements H5Contract.View {
    private WebView webview;
    private HashMap<String, String> extraHeaders;
    private String myurl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        webview = (WebView)findViewById(R.id.webview);
        myurl = AppConfig.BASE_URL + "/api/Movie/fun";
        Log.d("刚进来", "onCreate: "+my.SESSIONID);
        initwebview();
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
               return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    syncCookie(request.getUrl().toString());
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        webview.loadUrl(myurl);
    }
    private void syncCookie(String url) {
        if (my.SESSIONID.equals("")) {
            CookieSyncManager.createInstance(this);//创建一个cookie管理器
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();// 移除以前的cookie
            cookieManager.removeAllCookie();
            return;
        }
        try {
            CookieSyncManager.createInstance(this);//创建一个cookie管理器
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();// 移除以前的cookie
            cookieManager.removeAllCookie();
            cookieManager.setAcceptCookie(true);
            StringBuilder sbCookie = new StringBuilder();//创建一个拼接cookie的容器,为什么这么拼接，大家查阅一下http头Cookie的结构
            sbCookie.append("SESSION=" + my.SESSIONID);//拼接sessionId
            String cookieValue = sbCookie.toString();
            cookieManager.setCookie(url, cookieValue);//为url设置cookie
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
        } catch (Exception e) {
            Log.d("AppConfig.SESSIONID", "加载出错 "+my.SESSIONID);
            e.printStackTrace();
        }
    }
    private void initwebview() {
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);

    }
}
