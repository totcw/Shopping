package com.betterda.shopping.pay;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.pay.contract.PayContract;
import com.betterda.shopping.pay.presenter.PayPresenterImpl;

/**
 * Created by Administrator on 2016/12/15.
 */

public class PayActivity extends BaseActivity<PayContract.Presenter> implements PayContract.View {
    @Override
    protected PayContract.Presenter onLoadPresenter() {
        return new PayPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_pay);
    }
}
