package com.betterda.shopping.information.contract;

import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.home.contract.HomeContract;

/**
 * Created by Administrator on 2016/12/19.
 */

public class InformationContract {

    public interface View extends IView {
    }

    public interface Presenter extends IPresenter<View> {
    }

    public interface Model extends HomeContract.Model{
    }


}