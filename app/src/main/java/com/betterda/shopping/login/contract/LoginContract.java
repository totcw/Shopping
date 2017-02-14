package com.betterda.shopping.login.contract;

import android.view.View;
import android.widget.EditText;

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

    EditText getTvPwd();
}

public interface Presenter extends IPresenter<View>{
    void login();

    void loginThree(String s,String uid);
}

public interface Model extends IModel{
}


}