package com.betterda.shopping.order.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.javabean.OrderComfirm;

/**
 * Created by Administrator on 2016/12/20.
 */

public class OrderDetailContract {

    public interface View extends IView{
        void setValue(OrderComfirm data);
    }

    public interface Presenter extends IPresenter<View> {
        void comment();

        void pay();

        void cancel();
    }

    public interface Model extends IModel{
    }


}