package com.betterda.shopping.base;

import android.app.Activity;
import android.content.Context;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.RxManager;

/**
 * view的基类接口
 * Created by Administrator on 2016/12/2.
 */

public interface IView {
    Activity getmActivity();

    Context getContext();

    RxManager getRxManager();

    LoadingPager getLodapger();

    /**
     * 获取帐号
     * @return
     */
     String getAccount();

    /**
     * 获取token
     * @return
     */
     String getToken();
}
