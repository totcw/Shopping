package com.betterda.shopping.my;

import android.view.LayoutInflater;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.my.contract.MyContract;
import com.betterda.shopping.my.presenter.MyPresenterImpl;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MyFragment extends BaseFragment<MyContract.Presenter> implements MyContract.View {


    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_my,null);
    }
    @Override
    protected MyContract.Presenter onLoadPresenter() {
        return new MyPresenterImpl();
    }
}
