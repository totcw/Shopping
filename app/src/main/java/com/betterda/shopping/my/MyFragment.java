package com.betterda.shopping.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.mylibrary.view.IndicatorView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.my.contract.MyContract;
import com.betterda.shopping.my.presenter.MyPresenterImpl;
import com.betterda.shopping.order.OrderActivity;
import com.betterda.shopping.setting.SettingActivity;
import com.betterda.shopping.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MyFragment extends BaseFragment<MyContract.Presenter> implements MyContract.View {


    @BindView(R.id.sv_touxiang)
    ImageView mSvTouxiang;
    @BindView(R.id.tv_my_number)
    TextView mTvMyNumber;
    @BindView(R.id.relative_my_touxiang)
    RelativeLayout mRelativeMyTouxiang;
    @BindView(R.id.relative_my_setting)
    RelativeLayout mRelativeMySetting;
    @BindView(R.id.relative_my_message)
    RelativeLayout mRelativeMyMessage;
    @BindView(R.id.relative_my_all)
    RelativeLayout mRelativeMyAll;
    @BindView(R.id.idv_pay)
    IndicatorView mIdvPay;
    @BindView(R.id.idv_send)
    IndicatorView mIdvSend;
    @BindView(R.id.idv_bring)
    IndicatorView mIdvBring;
    @BindView(R.id.idv_take)
    IndicatorView mIdvTake;
    @BindView(R.id.idv_comment)
    IndicatorView mIdvComment;
    @BindView(R.id.relative_my_tuikuan)
    RelativeLayout mRelativeMyWallet;
    @BindView(R.id.relative_my_jiayouka)
    RelativeLayout mRelativeMyMember;//成为会员
    @BindView(R.id.relative_my_bus)
    RelativeLayout mTelativeMyTuijian;//立即推荐
    @BindView(R.id.relative_my_address)
    RelativeLayout mRelativeMyTuijian2;//我的推荐
    @BindView(R.id.relative_my_kefu)
    RelativeLayout mRelativeMyAddress;//常用地址

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_my, null);
    }

    @Override
    protected MyContract.Presenter onLoadPresenter() {
        return new MyPresenterImpl();
    }


    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏

        } else {
            StatusBarCompat.setStatusBar5(getmActivity(), R.color.backgroudyellow);
        }
    }



    @OnClick({R.id.relative_my_touxiang, R.id.relative_my_setting,R.id.relative_my_message, R.id.relative_my_all, R.id.relative_my_tuikuan, R.id.relative_my_jiayouka, R.id.relative_my_bus, R.id.relative_my_address, R.id.relative_my_kefu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_my_touxiang:
                break;
            case R.id.relative_my_setting:
                UiUtils.startIntent(getmActivity(), SettingActivity.class);
                break;
            case R.id.relative_my_message://消息

                break;
            case R.id.relative_my_all://我的订单
                UiUtils.startIntent(getmActivity(), OrderActivity.class);
                break;
            case R.id.relative_my_tuikuan://我的钱包
                break;
            case R.id.relative_my_jiayouka://成为会员
                break;
            case R.id.relative_my_bus://立即推荐
                break;
            case R.id.relative_my_address://我的推荐
                break;
            case R.id.relative_my_kefu://常用地址
                break;
        }
    }
}
