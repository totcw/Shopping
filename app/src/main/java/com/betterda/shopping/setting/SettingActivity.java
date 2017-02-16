package com.betterda.shopping.setting;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.FindPwdActivity;
import com.betterda.shopping.information.InformationActivity;
import com.betterda.shopping.setting.contract.SettingContract;
import com.betterda.shopping.setting.presenter.SettingPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.NormalTopBar;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 设置
 * Created by Administrator on 2016/12/19.
 */

public class SettingActivity extends BaseActivity<SettingContract.Presenter> implements SettingContract.View {
    @BindView(R.id.topbar_setting)
    NormalTopBar topbarSetting;
    @BindView(R.id.linear_setting_ziliao)
    LinearLayout linearSettingZiliao;
    @BindView(R.id.linear_setting_editpwd)
    LinearLayout linearSettingEditpwd;
    @BindView(R.id.linear_setting_us)
    LinearLayout linearSettingUs;
    @BindView(R.id.btn_setting_exit)
    Button btnSettingExit;

    @Override
    protected SettingContract.Presenter onLoadPresenter() {
        return new SettingPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_setting);
    }


    @Override
    public void init() {
        super.init();
        topbarSetting.setTitle("设置");
    }

    @OnClick({R.id.linear_setting_ziliao,R.id.bar_back, R.id.linear_setting_editpwd, R.id.linear_setting_us, R.id.btn_setting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_setting_ziliao:
                UtilMethod.isLogin(getmActivity(),InformationActivity.class);
                break;
            case R.id.linear_setting_editpwd:
                UiUtils.startIntent(getmActivity(), FindPwdActivity.class);
                break;
            case R.id.linear_setting_us:
                break;
            case R.id.btn_setting_exit:
                getPresenter().exit();
                break;
            case R.id.bar_back:
                back();
                break;
        }
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
