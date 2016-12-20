package com.betterda.shopping.order.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * fragment基类的contract
 * Created by Administrator on 2016/12/19.
 */

public class BaseOrderContract {

    public interface View extends IView{
    }

    public interface Presenter extends IPresenter<View> {
    }

    public interface Model extends IModel {
    }


}