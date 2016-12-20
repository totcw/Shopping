package com.betterda.shopping.order;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.order.contract.OrderDetailContract;
import com.betterda.shopping.order.presenter.OrderDetailPresenterImpl;

/**
 * Created by Administrator on 2016/12/20.
 */

public class OrderDetailActivity extends BaseActivity<OrderDetailContract.Presenter> implements OrderDetailContract.View {
    @Override
    protected OrderDetailContract.Presenter onLoadPresenter() {
        return new OrderDetailPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_orderdetail);
    }
}
