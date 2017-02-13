package com.betterda.shopping.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.contract.MyYinHangKaContract;
import com.betterda.shopping.wallet.presenter.MyYinHangKaPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的银行卡
 * Created by Administrator on 2016/12/27.
 */

public class MyYinHangKaActivity extends BaseActivity<MyYinHangKaContract.Presenter> implements MyYinHangKaContract.View {
    @BindView(R.id.topbar_myyinhangka)
    NormalTopBar mTopbarMyyinhangka;
    @BindView(R.id.layout_recycleview)
    XRecyclerView mRecycleview;


    @BindView(R.id.layout_loadingpager)
    LoadingPager mLoadingpager;

    @Override
    protected MyYinHangKaContract.Presenter onLoadPresenter() {
        return new MyYinHangKaPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_myyinhangka);
    }



    @Override
    public void init() {
        super.init();
        setTopBar();


        initRv();
    }

    private void setTopBar() {
        mTopbarMyyinhangka.setTitle("我的银行卡");
        mTopbarMyyinhangka.setActionText("添加");
        mTopbarMyyinhangka.setActionTextVisibility(true);
    }

    @OnClick({R.id.bar_back,R.id.bar_action})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_action://添加
                UiUtils.startIntent(getmActivity(),AddBankCardActivity.class);
                break;

            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    private void initRv() {
        mRecycleview.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setVisibility(View.VISIBLE);
        mRecycleview.setAdapter(getPresenter().getRvAdapter());

    }
    public NormalTopBar getNormalTopBar() {
        return mTopbarMyyinhangka;
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadingpager;
    }
}
