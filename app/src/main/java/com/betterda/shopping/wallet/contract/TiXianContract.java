package com.betterda.shopping.wallet.contract;


import android.view.View;
import android.widget.TextView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/28.
 */

public class TiXianContract {

    public interface View extends IView {
        String getMoney();

        TextView getTvBalance();
    }

    public interface Presenter extends IPresenter<View> {
        //全部提取
        void getAll();

        /**
         * 提现
         */
        void commit();

        void setBank(String bank, String bankCrad);
    }

    public interface Model extends IModel {
    }


}