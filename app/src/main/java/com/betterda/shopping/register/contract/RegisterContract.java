package com.betterda.shopping.register.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/20.
 */

public class RegisterContract {
    
public interface View extends IView{
}

public interface Presenter extends IPresenter<View>{
}

public interface Model extends IModel{
}


}