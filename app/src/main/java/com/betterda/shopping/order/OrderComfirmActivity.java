package com.betterda.shopping.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.order.contract.OrderComfrimContract;
import com.betterda.shopping.order.presenter.OrderComfrimPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 填写订单
 * Created by Administrator on 2016/12/14.
 */

public class OrderComfirmActivity extends BaseActivity<OrderComfrimContract.Presenter> implements OrderComfrimContract.View {
    @BindView(R.id.topbar_oder)
    NormalTopBar mTopbarOder;
    @BindView(R.id.tv_order_pay)
    TextView mTvOrderPay; //立即付款
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney; //实际付款的钱
    @BindView(R.id.tv_order_shouhuoren2)
    TextView mTvOrderShouhuoren2;//收货人
    @BindView(R.id.tv_order_address2)
    TextView mTvOrderAddress2;//收货地址
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;//电话号码
    @BindView(R.id.frame_address)
    FrameLayout mFrameAddress;//添加配送地址
    @BindView(R.id.rv_confirmorder)
    RecyclerView mRvConfirmorder;
    @BindView(R.id.tv_order_fapiao)
    TextView mTvOrderFapiao; //发票
    @BindView(R.id.relative_order_fapiao)
    RelativeLayout mRelativeOrderFapiao;
    @BindView(R.id.tv_order_youhuiquan)
    TextView mTvOrderYouhuiquan;
    @BindView(R.id.relative_order_youhuiquan)
    RelativeLayout mRelativeOrderYouhuiquan;
    @BindView(R.id.tv_order_peisong)
    TextView mTvOrderPeisong;
    @BindView(R.id.relative_order_peisong)
    RelativeLayout mRelativeOrderPeisong;
    @BindView(R.id.tv_confirmorder_price)
    TextView mTvConfirmorderPrice; //商品的总价
    @BindView(R.id.tv_confirmorder_yunfei)
    TextView mTvConfirmorderYunfei;
    @BindView(R.id.loadpager_order)
    LoadingPager mLoadpagerOrder;

    @Override
    protected OrderComfrimContract.Presenter onLoadPresenter() {
        return new OrderComfrimPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_confirmorder);
    }

    @Override
    public void init() {
        super.init();
        mTopbarOder.setTitle("填写订单");
    }

    @OnClick({R.id.tv_order_pay, R.id.frame_address, R.id.relative_order_fapiao, R.id.relative_order_youhuiquan, R.id.relative_order_peisong,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_order_pay://立即付款
                break;
            case R.id.frame_address://添加配送地址
                break;
            case R.id.relative_order_fapiao://发票
                break;
            case R.id.relative_order_youhuiquan://代金券
                break;
            case R.id.relative_order_peisong://配送方式
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    public void initRvOrder(RecyclerView.Adapter adapter) {
        mRvConfirmorder.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvConfirmorder.addItemDecoration(new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL_LIST));
        mRvConfirmorder.setAdapter(adapter);
    }
}
