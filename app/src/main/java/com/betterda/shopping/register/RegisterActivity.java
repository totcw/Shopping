package com.betterda.shopping.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.betterda.mylibrary.view.CountDown;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.register.contract.RegisterContract;
import com.betterda.shopping.register.presenter.RegisterPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * Created by Administrator on 2016/12/20.
 */

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements RegisterContract.View {
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
    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;
    @BindView(R.id.iv_register_choose)
    ImageView mIvRegisterChoose;

    @Override
    protected RegisterContract.Presenter onLoadPresenter() {
        return new RegisterPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_register);
    }

    @Override
    public void init() {
        super.init();
        mTopbarRegister.setTitle("注册");
    }

    @OnClick({R.id.countdown_register, R.id.bar_back,R.id.iv_register_choose, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.countdown_register:
                break;
            case R.id.iv_register_choose:
                mIvRegisterChoose.setSelected(!mIvRegisterChoose.isSelected());
                break;
            case R.id.btn_register:
                getPresenter().register();
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    public String getAccount() {
        return mEtRegisterNumber.getText().toString().trim();
    }

    @Override
    public String getYzm() {
        return mEtRegisterYzm.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtRegisterPwd.getText().toString().trim();
    }

    @Override
    public String getPwd2() {
        return mEtRegisterPwd2.getText().toString().trim();
    }

    @Override
    public String getNumber() {
        return mEtRegisterPhone.getText().toString().trim();
    }
}
