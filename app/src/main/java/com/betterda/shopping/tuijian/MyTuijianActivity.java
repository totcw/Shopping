package com.betterda.shopping.tuijian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.javabean.TuiJian;
import com.betterda.shopping.tuijian.contract.MyTuijianContract;
import com.betterda.shopping.tuijian.presenter.MyTuijianPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的推荐
 * Created by Administrator on 2016/12/20.
 */

public class MyTuijianActivity extends BaseActivity<MyTuijianContract.Presenter> implements MyTuijianContract.View {
    @BindView(R.id.topbar_wallet)
    NormalTopBar mTopbarWallet;
    @BindView(R.id.tv_wallet_jinbi)
    TextView mTvWalletJinbi;//推荐返现
    @BindView(R.id.relative_wallet_jinbi)
    RelativeLayout mRelativeWalletJinbi;
    @BindView(R.id.tv_wallet_yinbi)
    TextView mTvWalletYinbi; //推荐人数
    @BindView(R.id.relative_wallet_yinbi)
    RelativeLayout mRelativeWalletYinbi;
    @BindView(R.id.loadpager_wallet)
    LoadingPager mLoadpagerWallet;

    @Override
    protected MyTuijianContract.Presenter onLoadPresenter() {
        return new MyTuijianPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_tuijian);
    }

    @Override
    public void init() {
        super.init();
        mTopbarWallet.setTitle("我的推荐");
    }

    @OnClick({R.id.relative_wallet_jinbi, R.id.relative_wallet_yinbi,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_wallet_jinbi://推荐返现
                getPresenter().fanxian();
                break;
            case R.id.relative_wallet_yinbi://推荐人数
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    public void setValue(TuiJian data) {
        mTvWalletJinbi.setText("￥"+data.getHeapCashback());
        mTvWalletYinbi.setText(data.getNumber()+"人");
    }
}
