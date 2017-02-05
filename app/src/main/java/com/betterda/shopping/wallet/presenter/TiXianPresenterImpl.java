package com.betterda.shopping.wallet.presenter;


import android.content.Intent;
import android.text.TextUtils;

import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.contract.TiXianContract;

/**
 * 提现
* Created by Administrator on 2016/12/28
*/

public class TiXianPresenterImpl extends BasePresenter<TiXianContract.View,TiXianContract.Model> implements TiXianContract.Presenter{
    private String balance;//余额
    private String money;//提现的数量
    private String mBank;//所属银行
    private String mBankCard;//银行id


    @Override
    public void start() {
        Intent intent = getView().getmActivity().getIntent();
        if (intent != null) {
            balance=  intent.getStringExtra("money");
            if (balance == null) {
                balance = "0";
            }
        }
        getView().getTvBalance().setText(balance+"元");
    }
    @Override
    public void getAll() {
        NetworkUtils.isNetWork(getView().getmActivity(), getView().getTvBalance(), new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                getView().getRxManager().add(NetWork.getNetService()
                        .getCash(getView().getAccount(),getView().getToken(),balance,mBankCard)
                        .compose(NetWork.handleResult(new BaseCallModel<String>()))
                        .subscribe(new MyObserver<String>() {
                            @Override
                            protected void onSuccess(String data, String resultMsg) {
                                if (BuildConfig.LOG_DEBUG) {
                                    System.out.println("提现success:"+resultMsg);
                                }
                                getView().getmActivity().finish();
                            }

                            @Override
                            public void onFail(String resultMsg) {
                                if (BuildConfig.LOG_DEBUG) {
                                    System.out.println("提现fail:"+resultMsg);
                                }
                            }

                            @Override
                            public void onExit() {

                            }
                        }));
            }
        });
    }

    @Override
    public void commit() {

        try {
            if (TextUtils.isEmpty(mBankCard)) {
                UiUtils.showToast(getView().getmActivity(), "请选择银行卡");
                return;
            }
            money=  getView().getMoney();
            if (TextUtils.isEmpty(money)) {
                UiUtils.showToast(getView().getmActivity(), "请输入提现金额");
                return;
            }else if ("0".equals(money)) {

                UiUtils.showToast(getView().getmActivity(), "提现的数量不能为0");
                return;
            } else if (Float.parseFloat(money) > Float.parseFloat(balance)) {
                UiUtils.showToast(getView().getmActivity(), "超出可提现金额");
                return;
            }
            NetworkUtils.isNetWork(getView().getmActivity(), getView().getTvBalance(), new NetworkUtils.SetDataInterface() {
                @Override
                public void getDataApi() {
                    getView().getRxManager().add(NetWork.getNetService()
                            .getCash(getView().getAccount(),getView().getToken(),money,mBankCard)
                            .compose(NetWork.handleResult(new BaseCallModel<String>()))
                            .subscribe(new MyObserver<String>() {
                                @Override
                                protected void onSuccess(String data, String resultMsg) {
                                    if (BuildConfig.LOG_DEBUG) {
                                        System.out.println("提现success:"+resultMsg);
                                    }
                                    getView().getmActivity().finish();
                                }

                                @Override
                                public void onFail(String resultMsg) {
                                    if (BuildConfig.LOG_DEBUG) {
                                        System.out.println("提现fail:"+resultMsg);
                                    }
                                }

                                @Override
                                public void onExit() {

                                }
                            }));
                }
            });
        } catch (Exception e) {

        }


    }

    @Override
    public void setBank(String bank, String bankCrad) {
        this.mBank = bank;
        this.mBankCard = bankCrad;
    }


    @Override
    public void destroy() {

    }


}