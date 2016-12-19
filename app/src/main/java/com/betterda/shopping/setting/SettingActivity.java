package com.betterda.shopping.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.information.InformationActivity;
import com.betterda.shopping.setting.contract.SettingContract;
import com.betterda.shopping.setting.presenter.SettingPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    @OnClick({R.id.linear_setting_ziliao, R.id.linear_setting_editpwd, R.id.linear_setting_us, R.id.btn_setting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_setting_ziliao:
                UiUtils.startIntent(getmActivity(), InformationActivity.class);
                break;
            case R.id.linear_setting_editpwd:
                break;
            case R.id.linear_setting_us:
                break;
            case R.id.btn_setting_exit:
                break;
        }
    }
}
