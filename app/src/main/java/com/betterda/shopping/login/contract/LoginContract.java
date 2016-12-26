package com.betterda.shopping.login.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.utils.RxManager;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LoginContract {
    
public interface View extends IView{

    String getAccount();

    String getPwd();
}

public interface Presenter extends IPresenter<View>{
    void login();
}

public interface Model extends IModel{
}


}