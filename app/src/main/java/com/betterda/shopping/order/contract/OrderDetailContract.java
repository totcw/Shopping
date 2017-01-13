package com.betterda.shopping.order.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/20.
 */

public class OrderDetailContract {

    public interface View extends IView{
    }

    public interface Presenter extends IPresenter<View> {
        void comment();
    }

    public interface Model extends IModel{
    }


}