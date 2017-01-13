package com.betterda.shopping.wallet.presenter;


import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.utils.NetworkUtils;
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
        getView().getTvBalance().setText(balance);
    }
    @Override
    public void getAll() {

    }

    @Override
    public void commit() {
        money=  getView().getMoney();

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