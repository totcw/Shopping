package com.betterda.shopping.pay;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;


/**
 * 图文详细
 * Created by Administrator on 2016/5/30.
 */
public class JsActivity extends BaseActivity {

    private WebView webView;
    private String orderId;
    private float money;

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_params);
        webView = (WebView) findViewById(R.id.slidedetails_behind);
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");
            money = intent.getFloatExtra("money", 0);
        }
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JsInterce(),"pay");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
               // setParams("dsaf454","100");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            new Object() {
                public void setLoadWithOverviewMode(boolean overview) {
                    settings.setLoadWithOverviewMode(overview);
                }
            }.setLoadWithOverviewMode(true);
        }

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

                String url = "http://192.168.1.104:8080/POS_TRADE_WAP/index.jsp?orderId="+orderId+"&money="+money;
                webView.loadUrl(url);

            }
        });
    }

    public class JsInterce{
        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void showInfoFromJs(String name) {
            System.out.println("js中调用了showInforFormJs方法");
        }
    }

    //在java中调用js代码
    public void sendInfoToJs() {
        String msg = "在android中调用了js的方法";
        //调用js中的函数：showInfoFromJava(msg)
        webView.loadUrl("javascript:showInfoFromJava('" + msg + "')");
    }

    public void setParams(String orderId, String money) {
        webView.loadUrl(String.format("javascript:setParams("+ orderId +", "+ money +")"));
    }
}
