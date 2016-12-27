package com.betterda.shopping.wallet.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/27.
 */

public class AddBankCardContract {

    public interface View extends IView{
    }

    public interface Presenter  extends IPresenter<View>{
        /**
         * 设置选择的银行
         * @param s
         */
        void setBank(String s);
    }

    public interface Model extends IModel {
    }


}