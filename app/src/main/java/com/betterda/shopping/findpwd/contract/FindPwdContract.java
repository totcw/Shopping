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
}

public interface Presenter extends IPresenter<View>{
    void countDown();
}

public interface Model extends IModel{
}


}