package com.betterda.shopping.find.presenter;
import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.find.contract.FindContract;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.Address;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.Location;
import com.betterda.shopping.utils.UtilMethod;

import java.util.List;

/**
* Created by Administrator on 2016/12/08
*/

public class FindPresenterImpl  extends BasePresenter<FindContract.View,FindContract.Model> implements FindContract.Presenter{

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getData(double longitude, double latitude) {
        System.out.println("附近商家");
        getView().getRxManager().add(NetWork.getNetService()
                .getShop(longitude+"",latitude+"")
                .compose(NetWork.handleResult(new BaseCallModel<List<Location>>()))
                .subscribe(new MyObserver<List<Location>>() {
                    @Override
                    protected void onSuccess(List<Location> data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("附近商家success:"+data.toString());
                        }
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("附近商家fail:"+resultMsg);
                        }
                    }

                    @Override
                    public void onExit() {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("附近商家failtoken");
                        }
                    }
                })
        );
    }
}