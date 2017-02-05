package com.betterda.shopping.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.FindPwdActivity;
import com.betterda.shopping.login.contract.LoginContract;
import com.betterda.shopping.login.presenter.LoginPresenterImpl;
import com.betterda.shopping.register.RegisterActivity;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
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
                loginThree("微信");
                break;
            case R.id.relative_login_weibo:
                loginThree("微博");
                break;
            case R.id.relative_login_QQ:
                loginThree("QQ");
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
     * 三方登录
     *
     * @param type
     */
    private void loginThree(final String type) {
        try {
            NetworkUtils.isNetWork(getmActivity(), mTopbarLogin, new NetworkUtils.SetDataInterface() {
                @Override
                public void getDataApi() {
                    UMShareAPI mShareAPI = UMShareAPI.get(getmActivity());
                    switch (type) {
                        case "微信":
                            mShareAPI.doOauthVerify(getmActivity(), SHARE_MEDIA.WEIXIN, umAuthListener);
                            break;
                        case "微博":
                            mShareAPI.doOauthVerify(getmActivity(), SHARE_MEDIA.SINA, umAuthListener);
                            break;
                        case "QQ":
                            boolean install = mShareAPI.isInstall(LoginActivity.this, SHARE_MEDIA.QQ);
                            if (install) {
                                mShareAPI.doOauthVerify(getmActivity(), SHARE_MEDIA.QQ, umAuthListener);
                            } else {
                                UiUtils.showToast(getmActivity(), "请先安装qq");
                            }
                            break;
                    }
                }
            });
        } catch (Exception e) {

        }


    }


    /**
     * 友盟的回调
     */
    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            /*Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> m : entries) {
                System.out.println(m.getKey() + ":" + m.getValue());
            }*/

            String userName = map.get("userName");
            String uid = map.get("uid");
            String icon_url = map.get("icon_url");
            System.out.println("loginthree:  "+" username:"+userName+"uid:"+uid+"url:"+icon_url+"type:"+share_media);
            getPresenter().loginThree(share_media.toString(), uid);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            log(share_media.toString() + "," + throwable.toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            log(share_media.toString());
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
