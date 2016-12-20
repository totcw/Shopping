package com.betterda.shopping.register;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.register.contract.RegisterContract;
import com.betterda.shopping.register.presenter.RegisterPresenterImpl;

/**
 * Created by Administrator on 2016/12/20.
 */

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements  RegisterContract.View {
    @Override
    protected RegisterContract.Presenter onLoadPresenter() {
        return new RegisterPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_register);
    }
}
