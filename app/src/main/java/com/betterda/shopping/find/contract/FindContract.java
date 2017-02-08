package com.betterda.shopping.find.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class FindContract {

    public interface View extends IView {
        void marker(double latitude, double longitude, String distance,String title);
    }

    public interface Presenter extends IPresenter<View> {
        void getData(double longitude, double latitude);
    }

    public interface Model extends IModel{
    }


}