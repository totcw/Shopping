package com.betterda.shopping.login;

import android.content.Intent;
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
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.RxManager;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
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

    @OnClick({R.id.tv_login_pwd, R.id.bar_back, R.id.btn_login, R.id.relative_login_register, R.id.relative_login_wx, R.id.relative_login_weibo, R.id.relative_login_QQ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_pwd://忘记密码
                UiUtils.startIntent(getmActivity(), FindPwdActivity.class);
                break;
            case R.id.btn_login:
                NetworkUtils.isNetWork(getmActivity(), mTopbarLogin, new NetworkUtils.SetDataInterface() {
                    @Override
                    public void getDataApi() {
                        getPresenter().login();
                    }
                });
                break;
            case R.id.relative_login_register:
                UiUtils.startIntent(getmActivity(), RegisterActivity.class);
                break;
            case R.id.relative_login_wx:
                break;
            case R.id.relative_login_weibo:
                UMShareAPI mShareAPI = UMShareAPI.get(this);
                mShareAPI.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.relative_login_QQ:
                UMShareAPI mQqShareAPI = UMShareAPI.get(this);
                mQqShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //qq需要
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }



    /**
     * 友盟的回调
     */
    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };




    @Override
    public String getAccount() {
        return mEtLoginNumber.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtLoginPwd.getText().toString().trim();
    }

}
