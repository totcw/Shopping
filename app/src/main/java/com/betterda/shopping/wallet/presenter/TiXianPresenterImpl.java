package com.betterda.shopping.wallet.presenter;


import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.wallet.contract.TiXianContract;

/**
* Created by Administrator on 2016/12/28
*/

public class TiXianPresenterImpl extends BasePresenter<TiXianContract.View,TiXianContract.Model> implements TiXianContract.Presenter{
    private String balance;//余额
    private String money;//提现的数量
    private String mBank;//所属银行
    private String mBankCard;//银行卡号


    @Override
    public void start() {

    }
    @Override
    public void getAll() {

    }

    @Override
    public void commit() {
        money=  getView().getMoney();
    }

    @Override
    public void setBank(String bank, String bankCrad) {
        bank = bank;
        bankCrad = bankCrad;
    }


    @Override
    public void destroy() {

    }


}