package com.betterda.shopping.address;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.address.contract.AddressContract;
import com.betterda.shopping.address.presenter.AddressPresenterImpl;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址
 * Created by Administrator on 2016/12/16.
 */

public class AddressActivity extends BaseActivity<AddressContract.Presenter> implements AddressContract.View {
    @BindView(R.id.layout_recycleview)
    XRecyclerView mXRecycleview;
    @BindView(R.id.layout_loadingpager)
    LoadingPager mLoadingpager;
    @BindView(R.id.topbar_adddress)
    NormalTopBar mTopBarAddress;

    @Override
    protected AddressContract.Presenter onLoadPresenter() {
        return new AddressPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_address);
    }

    @Override
    public void init() {
        super.init();
        mTopBarAddress.setTitle("常用地址");
        mTopBarAddress.setActionText("添加");
        mTopBarAddress.setActionTextVisibility(true);

        mLoadingpager.setonErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onError();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().getData();
    }

    public void initRv(RecyclerView.Adapter adapter) {
        mXRecycleview.setVisibility(View.VISIBLE);
        mXRecycleview.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mXRecycleview.setAdapter(adapter);

    }

    @OnClick({ R.id.bar_back,R.id.bar_action})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_action:
                UiUtils.startIntent(getmActivity(),AddAddressActivity.class);
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadingpager;
    }
}
