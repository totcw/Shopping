package com.betterda.shopping.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.FindPwdActivity;
import com.betterda.shopping.login.contract.LoginContract;
import com.betterda.shopping.login.presenter.LoginPresenterImpl;
import com.betterda.shopping.register.RegisterActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.topbar_login)
    NormalTopBar mTopbarLogin;
    @BindView(R.id.et_login_number)
    EditText mEtLoginNumber;
    @BindView(R.id.et_login_pwd)
    EditText mEtLoginPwd;

    @Override
    protected LoginContract.Presenter onLoadPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void init() {
        super.init();
        mTopbarLogin.setTitle("登录");
    }

    @OnClick({R.id.tv_login_pwd,R.id.bar_back, R.id.btn_login, R.id.relative_login_register, R.id.relative_login_wx, R.id.relative_login_weibo, R.id.relative_login_QQ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_pwd://忘记密码
                UiUtils.startIntent(getmActivity(), FindPwdActivity.class);
                break;
            case R.id.btn_login:
                break;
            case R.id.relative_login_register:
                UiUtils.startIntent(getmActivity(), RegisterActivity.class);
                break;
            case R.id.relative_login_wx:
                break;
            case R.id.relative_login_weibo:
                break;
            case R.id.relative_login_QQ:
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }
}
