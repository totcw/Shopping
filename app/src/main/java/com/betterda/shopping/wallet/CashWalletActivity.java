package com.betterda.shopping.wallet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.contract.CashWalletContract;
import com.betterda.shopping.wallet.presenter.CashWalletPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 现金钱包
 * Created by Administrator on 2016/12/20.
 */

public class CashWalletActivity extends BaseActivity<CashWalletContract.Presenter> implements CashWalletContract.View {
    @BindView(R.id.topbar_cashwallet)
    public NormalTopBar mTopbarCashwallet;
    @BindView(R.id.tv_wallet2_money)
    public TextView mTvWallet2Money;
    @BindView(R.id.relative_wallet2_chongzhi)
    public RelativeLayout mRelativeWallet2Chongzhi;
    @BindView(R.id.loadpager_cashwallet)
    public LoadingPager mLoadpagerCashwallet;

    @Override
    protected CashWalletContract.Presenter onLoadPresenter() {
        return new CashWalletPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_cashwallet);
    }

    @Override
    public void init() {
        super.init();
        mTopbarCashwallet.setTitle("现金钱包");
        mTopbarCashwallet.setActionText("明细");
        mTopbarCashwallet.setActionTextVisibility(true);
    }

    @OnClick({R.id.relative_wallet2_chongzhi, R.id.bar_back, R.id.bar_action,R.id.tv_cashwallet_shouming})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.bar_action:
                UiUtils.startIntent(getmActivity(),MingXiActivity.class);
                break;
            case R.id.relative_wallet2_chongzhi://提现
                UiUtils.startIntent(getmActivity(),TiXianActivity.class);
                break;
            case R.id.tv_cashwallet_shouming://说明
                AlertDialog.Builder builder = new AlertDialog.Builder(getmActivity());
                builder.setTitle("现金钱包说明")
                        .setMessage("现金钱包是可以提现的")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                builder.show();
                break;
        }

    }
}
