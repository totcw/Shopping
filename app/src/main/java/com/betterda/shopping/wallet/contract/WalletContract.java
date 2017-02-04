package com.betterda.shopping.wallet.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.javabean.Wallet;

/**
 * Created by Administrator on 2016/12/20.
 */

public class WalletContract {

    public interface View extends IView{
        void setValue(Wallet data);
    }

    public interface Presenter extends IPresenter<View>{
    }

    public interface Model extends IModel{
    }


}