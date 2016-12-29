package com.betterda.shopping.order.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/14.
 */

public class OrderComfrimContract {

    public interface View extends IView{
        void initRvOrder(RecyclerView.Adapter adapter);
        //设置配送方法
        void setPeisong(String type);

        //设置是否需要发票
        void setFapiao(String fapiao);
    }

    public interface Presenter extends IPresenter<View> {
        //设置收货地址
        void setAddress(String name, String number, String area, String address);

        void ziti();

        void kuaidi();

        void shi();

        void fou();
    }

    public interface Model extends IModel{
    }


}