package com.betterda.shopping.my.presenter;
import android.net.Uri;
import android.text.TextUtils;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.my.contract.MyContract;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.UtilMethod;

/**
* Created by Administrator on 2016/12/08
*/

public class MyPresenterImpl extends BasePresenter<MyContract.View,MyContract.Model> implements MyContract.Presenter{

    @Override
    public void start() {

    }

    @Override
    public void onStart() {
        //如果登录就从本地获取头像和帐号信息
        if (UtilMethod.isLogin(getView().getmActivity())) {
            String number = CacheUtils.getString(getView().getmActivity(), Constants.Cache.ACCOUNT, "");
            if (!TextUtils.isEmpty(number)) {
                if (getView().getTextViewNumber() != null) {
                    //显示用户的昵称
                    String nickname = CacheUtils.getString(getView().getmActivity(), number +Constants.Cache.ACCOUNT, "还没有昵称呢");
                    getView().getTextViewNumber().setText(nickname);
                }
                String touxiang = CacheUtils.getString(getView().getmActivity(), number + Constants.Cache.TOUXIANG, "");

                if (!TextUtils.isEmpty(touxiang)) {
                    if (getView().getSimpleDraweeView() != null) {
                        LoadImageFactory.getLoadImageInterface().loadImageFit(getView().getmActivity(),touxiang,getView().getSimpleDraweeView());
                    }

                }
            }

        } else {
            //没有登录就设置为默认的
            if (getView().getTextViewNumber() != null) {
                getView().getTextViewNumber().setText("暂未登录");
            }
            if (getView().getSimpleDraweeView() != null) {
                getView().getSimpleDraweeView().setImageResource(R.mipmap.zwt_zheng);
            }
        }
    }

    @Override
    public void destroy() {

    }
}