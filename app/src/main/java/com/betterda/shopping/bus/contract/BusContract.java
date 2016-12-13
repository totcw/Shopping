package com.betterda.shopping.bus.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/13.
 */

public class BusContract {

    public interface View extends IView{
    }

    public interface Presenter extends IPresenter<View>{
    }

    public interface Model extends IModel {
    }


}