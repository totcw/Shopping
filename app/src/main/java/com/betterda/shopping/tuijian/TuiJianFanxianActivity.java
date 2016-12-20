package com.betterda.shopping.tuijian;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 推荐返现
 * Created by Administrator on 2016/12/20.
 */

public class TuiJianFanxianActivity extends BaseActivity {
    @BindView(R.id.topbar_cashwallet)
    NormalTopBar mTopbarCashwallet;
    @BindView(R.id.tv_wallet2_money)
    TextView mTvWallet2Money;
    @BindView(R.id.loadpager_cashwallet)
    LoadingPager mLoadpagerCashwallet;

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_tuijianfanxian);
    }

    @Override
    public void init() {
        super.init();
        mTopbarCashwallet.setTitle("推荐返现");
    }

    @OnClick({R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bar_back:
                back();
                break;
        }
    }
}
