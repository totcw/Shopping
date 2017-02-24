package com.betterda.shopping.tuijian;

import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;

import butterknife.BindView;

/**
 * 成为会员的界面
 * Created by Administrator on 2017/2/20.
 */

public class MyMemberActivity extends BaseActivity {
    @BindView(R.id.slidedetails_behind)
    WebView mWebView;
    private String account;

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_params);
    }

    @Override
    public void init() {
        super.init();
        account = CacheUtils.getString(getmActivity(), Constants.Cache.ACCOUNT, "");
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        mWebView.addJavascriptInterface(new JsInterce(),"member");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
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
                String url = "http://192.168.1.104:8080/WinePIN/tOnlinePayController.do?vipPay&account="+account;
                mWebView.loadUrl(url);

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
}