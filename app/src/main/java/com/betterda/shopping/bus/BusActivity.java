package com.betterda.shopping.bus;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.bus.contract.BusContract;
import com.betterda.shopping.bus.presenter.BusPresenterImpl;

/**
 * Created by Administrator on 2016/12/13.
 */

public class BusActivity extends BaseActivity<BusContract.Presenter> implements BusContract.View {
    @Override
    protected BusContract.Presenter onLoadPresenter() {
        return new BusPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_bus);
    }
}
