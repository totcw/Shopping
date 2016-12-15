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
    }

    public interface Presenter extends IPresenter<View> {
    }

    public interface Model extends IModel{
    }


}