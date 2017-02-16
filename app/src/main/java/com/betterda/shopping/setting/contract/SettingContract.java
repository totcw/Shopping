package com.betterda.shopping.setting.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class SettingContract {

    public interface View extends IView{
        void setAlias(String s);
    }

    public interface Presenter extends IPresenter<View>{
        void exit();
    }

    public interface Model extends IModel {
    }


}