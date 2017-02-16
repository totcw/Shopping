package com.betterda.shopping.login;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.FindPwdActivity;
import com.betterda.shopping.home.MainActivity;
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
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

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
        mTopbarLogin.setBackVisibility(false);
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

    @Override
    public EditText getTvPwd() {
        return mEtLoginPwd;
    }

    @Override
    public void onBackPressed() {
        UiUtils.startIntent(getmActivity(),MainActivity.class);
    }


    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    public void setAlias(String alias) {

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";

                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    break;
            }

        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:

                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    break;
            }
        }
    };
}
