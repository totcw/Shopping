package com.betterda.shopping.order.contract;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.javabean.DefaultAddress;

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
        //获取运费
        String getFreight();

        void setValue(DefaultAddress data);

        void setFregiht(String fregiht);
        //设置代金卷
        void setDaijinjuan(float voucher);
        //设置已使用的代金卷
        void setDaijinjuan2(float voucher);
        //设置商品金额
        void setMoney(float money);

        void setPayMoeny(float money);

        TextView getTvPay();
    }

    public interface Presenter extends IPresenter<View> {
        //设置收货地址
        void setAddress(String name, String number, String area, String address);

        void ziti();

        void kuaidi();

        void shi();

        void fou();
        //提交订单
        void commit();

        void editDaijinjuan();

        void onError();
    }

    public interface Model extends IModel{
    }


}