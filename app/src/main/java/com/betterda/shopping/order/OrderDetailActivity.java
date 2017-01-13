package com.betterda.shopping.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.comment.AddCommentActivity;
import com.betterda.shopping.javabean.AddComment;
import com.betterda.shopping.javabean.Bus;
import com.betterda.shopping.order.contract.OrderDetailContract;
import com.betterda.shopping.order.presenter.OrderDetailPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情
 * Created by Administrator on 2016/12/20.
 */

public class OrderDetailActivity extends BaseActivity<OrderDetailContract.Presenter> implements OrderDetailContract.View {
    @BindView(R.id.topbar_oderdetail)
    NormalTopBar mTopbarOderdetail;
    @BindView(R.id.relative_orderdetail_delete)
    RelativeLayout mRelativeOrderdetailDelete;//取消订单
    @BindView(R.id.relative_orderdetail_pay)
    RelativeLayout mRelativeOrderdetailPay;//立即付款
    @BindView(R.id.relative_orderdetail_comment)
    RelativeLayout mRelativeOrderdetailComment;//立即评价
    @BindView(R.id.linear_orderdetail_pay)
    LinearLayout mLinearOrderdetailPay;
    @BindView(R.id.tv_orderdetail_state)
    TextView mTvOrderdetailState;//交易状态
    @BindView(R.id.tv_orderdetail_price)
    TextView mTvOrderdetailPrice;
    @BindView(R.id.tv_orderdetail_shouhuoren2)
    TextView mTvOrderdetailShouhuoren2;
    @BindView(R.id.tv_orderdetail_address2)
    TextView mTvOrderdetailAddress2;
    @BindView(R.id.tv_orderdetail_number)
    TextView mTvOrderdetailNumber;
    @BindView(R.id.rv_orderdetail)
    RecyclerView mRvOrderdetail;
    @BindView(R.id.tv_orderdetail_yunfei)
    TextView mTvOrderdetailYunfei;
    @BindView(R.id.tv_orderdetail_daijinjuan)
    TextView mTvOrderdetailDaijinjuan;
    @BindView(R.id.tv_orderdetail_heji_money)
    TextView mTvOrderdetailHejiMoney;
    @BindView(R.id.tv_orderdetail_amnout)
    TextView mTvOrderdetailAmnout;
    @BindView(R.id.tv_orderdetail_fapiao)
    TextView mTvOrderdetailFapiao;
    @BindView(R.id.tv_orderdetail_peisong)
    TextView mTvOrderdetailPeisong;
    @BindView(R.id.tv_orderdetail_biamhao)
    TextView mTvOrderdetailBiamhao;
    @BindView(R.id.tv_orderdetail_time)
    TextView mTvOrderdetailTime;
    @BindView(R.id.loadpager_orderdetail)
    LoadingPager mLoadpagerOrderdetail;

    @Override
    protected OrderDetailContract.Presenter onLoadPresenter() {
        return new OrderDetailPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_orderdetail);
    }

    @Override
    public void init() {
        super.init();
        mTopbarOderdetail.setTitle("订单明细");
        mRelativeOrderdetailComment.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.relative_orderdetail_delete, R.id.relative_orderdetail_pay,
            R.id.bar_back,R.id.relative_orderdetail_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_orderdetail_delete://取消订单
                break;
            case R.id.relative_orderdetail_pay://立即付款
                break;
            case R.id.relative_orderdetail_comment://立即评价
                getPresenter().comment();

                break;
            case R.id.bar_back:
                back();
                break;

        }
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadpagerOrderdetail;
    }
}
