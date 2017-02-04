package com.betterda.shopping.pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.pay.contract.PayContract;
import com.betterda.shopping.pay.presenter.PayPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 付款界面
 * Created by Administrator on 2016/12/15.
 */

public class PayActivity extends BaseActivity<PayContract.Presenter> implements PayContract.View {
    @BindView(R.id.topbar_pay)
    NormalTopBar mTopbarPay;
    @BindView(R.id.tv_pay_money)
    TextView mTvPayMoney;
    private float money;
    private String orderId;

    @Override
    protected PayContract.Presenter onLoadPresenter() {
        return new PayPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_pay);
    }

    @Override
    public void init() {
        super.init();
        mTopbarPay.setTitle("支付中心");
        Intent intent = getIntent();
        if (intent != null) {
            orderId =intent.getStringExtra("orderId");
            money = intent.getFloatExtra("money", 0);
        }
        mTvPayMoney.setText("￥:"+money);
    }



    @OnClick({R.id.relative_pay_wxpay, R.id.relative_pay_zfbpay, R.id.relative_pay_wypay, R.id.btn_pay_confirm,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_pay_wxpay:
                break;
            case R.id.relative_pay_zfbpay:
                break;
            case R.id.relative_pay_wypay:
                break;
            case R.id.btn_pay_confirm:
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }
}
