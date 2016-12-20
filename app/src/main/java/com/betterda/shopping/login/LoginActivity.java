package com.betterda.shopping.login;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.login.contract.LoginContract;
import com.betterda.shopping.login.presenter.LoginPresenterImpl;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @Override
    protected LoginContract.Presenter onLoadPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_login);
    }
}
