package com.betterda.shopping.findpwd.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FindPwdContract {
    
public interface View extends IView{
    void showCountDown();

    String getAccount();

    String getPwd();

    String getPwd2();

    String getYzm();
}

public interface Presenter extends IPresenter<View>{
    void countDown();

    void register();
}

public interface Model extends IModel{
}


}