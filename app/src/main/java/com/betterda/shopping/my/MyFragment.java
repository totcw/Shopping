package com.betterda.shopping.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.mylibrary.view.IndicatorView;
import com.betterda.shopping.R;
import com.betterda.shopping.address.AddressActivity;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.findpwd.FindPwdActivity;
import com.betterda.shopping.findpwd.contract.FindPwdContract;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.login.LoginActivity;
import com.betterda.shopping.my.contract.MyContract;
import com.betterda.shopping.my.presenter.MyPresenterImpl;
import com.betterda.shopping.order.OrderActivity;
import com.betterda.shopping.register.RegisterActivity;
import com.betterda.shopping.setting.SettingActivity;
import com.betterda.shopping.tuijian.LiJiTuijianActivity;
import com.betterda.shopping.tuijian.MyTuijianActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.WalletActivity;
import com.betterda.shopping.ziti.ZiTiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的
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
        initIdv();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏

        } else {
            StatusBarCompat.setStatusBar5(getmActivity(), R.color.backgroudyellow);
            ((MainActivity)getmActivity()).getmBvMain().setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.relative_my_touxiang, R.id.relative_my_setting, R.id.relative_my_message, R.id.relative_my_all,
            R.id.relative_my_tuikuan, R.id.relative_my_jiayouka, R.id.relative_my_bus,
            R.id.relative_my_address, R.id.relative_my_kefu, R.id.idv_pay, R.id.idv_send,
            R.id.idv_bring, R.id.idv_take, R.id.idv_comment,R.id.relative_my_ziti})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_my_touxiang:
                UiUtils.startIntent(getmActivity(), LoginActivity.class);
                break;
            case R.id.relative_my_setting:
                UiUtils.startIntent(getmActivity(), SettingActivity.class);
                break;
            case R.id.relative_my_message://消息
                break;
            case R.id.relative_my_all://我的订单
                startToOrder(0);
                break;
            case R.id.idv_pay://待付款
                startToOrder(1);
                break;
            case R.id.idv_send:
                startToOrder(2);
                break;
            case R.id.idv_bring:
                startToOrder(3);
                break;
            case R.id.idv_take:
                startToOrder(4);
                break;
            case R.id.idv_comment:
                startToOrder(5);
                break;
            case R.id.relative_my_tuikuan://我的钱包
                UiUtils.startIntent(getmActivity(), WalletActivity.class);
                break;
            case R.id.relative_my_jiayouka://成为会员
                break;
            case R.id.relative_my_bus://立即推荐
                UiUtils.startIntent(getmActivity(), LiJiTuijianActivity.class);
                break;
            case R.id.relative_my_address://我的推荐
                UiUtils.startIntent(getmActivity(), MyTuijianActivity.class);
                break;
            case R.id.relative_my_ziti://自提码
                UiUtils.startIntent(getmActivity(), ZiTiActivity.class);
                break;
            case R.id.relative_my_kefu://常用地址
                Intent intent = new Intent(getmActivity(), AddressActivity.class);
                intent.putExtra("isMy", true);
                UiUtils.startIntent(getmActivity(), intent);
                break;
        }
    }


    private void initIdv() {
        mIdvPay.setTitle("待付款");
        mIdvSend.setTitle("待发货");
        mIdvBring.setTitle("待收货");
        mIdvTake.setTitle("待提货");
        mIdvComment.setTitle("待评价");

        mIdvPay.setIvBackground(R.mipmap.dpay, R.mipmap.dpay);
        mIdvSend.setIvBackground(R.mipmap.dsend, R.mipmap.dsend);
        mIdvBring.setIvBackground(R.mipmap.dbring, R.mipmap.dbring);
        mIdvTake.setIvBackground(R.mipmap.dtake, R.mipmap.dtake);
        mIdvComment.setIvBackground(R.mipmap.dcomment, R.mipmap.dcomment);


    }

    /**
     * 跳转到订单界面
     *
     * @param item
     */
    private void startToOrder(int item) {
        Intent intent = new Intent(getmActivity(), OrderActivity.class);
        intent.putExtra("item", item);
        UiUtils.startIntent(getmActivity(), intent);
    }

}
