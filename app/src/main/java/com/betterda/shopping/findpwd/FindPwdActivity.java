package com.betterda.shopping.findpwd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.betterda.mylibrary.view.CountDown;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.contract.FindPwdContract;
import com.betterda.shopping.findpwd.presenter.FindPwdPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FindPwdActivity extends BaseActivity<FindPwdContract.Presenter> implements FindPwdContract.View {
    @BindView(R.id.topbar_register)
    NormalTopBar mTopbarRegister;
    @BindView(R.id.et_register_number)
    EditText mEtRegisterNumber;
    @BindView(R.id.et_register_yzm)
    EditText mEtRegisterYzm;
    @BindView(R.id.countdown_register)
    CountDown mCountdownRegister;
    @BindView(R.id.et_register_pwd)
    EditText mEtRegisterPwd;
    @BindView(R.id.et_register_pwd2)
    EditText mEtRegisterPwd2;

    @Override
    protected FindPwdContract.Presenter onLoadPresenter() {
        return new FindPwdPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_findpwd);
    }

    @Override
    public void init() {
        super.init();
        mTopbarRegister.setTitle("修改密码");
        mCountdownRegister.setSelected(true);
    }

    @OnClick({R.id.countdown_register, R.id.btn_register,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.countdown_register:
                if (mCountdownRegister.isSelected()) {
                    getPresenter().countDown();
                }
                break;
            case R.id.btn_register:
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    public void showCountDown() {
        mCountdownRegister.showCountDown("秒后重新获取","60秒后重新获取");
    }
}
