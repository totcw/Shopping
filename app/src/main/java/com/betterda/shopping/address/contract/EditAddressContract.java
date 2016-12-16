package com.betterda.shopping.address.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class EditAddressContract {

    public interface View extends IView{
        String getNameText();
        String getNumberText();

        String getAreaText();

        String getaddressText();
    }

    public interface Presenter extends IPresenter<View>{
        void onToggle(boolean on);
        //保存
        void save();
        //删除
        void delete();
    }

    public interface Model extends IModel{
    }


}