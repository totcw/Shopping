package com.betterda.shopping.address.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/15.
 */

public class AddAddressContract {

    public interface View extends IView{
        String getNameText();

        String getNumberText();

        String getAreaText();

        String getaddressText();
    }

    public interface Presenter extends IPresenter<View> {
        //判断togglebutton的开关状态
        void onToggle(boolean on);
        //保存
        void save();
    }

    public interface Model extends IModel{
    }


}