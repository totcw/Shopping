package com.betterda.shopping.order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.address.AddressActivity;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.order.contract.OrderComfrimContract;
import com.betterda.shopping.order.presenter.OrderComfrimPresenterImpl;
import com.betterda.shopping.pay.PayActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 填写订单
 * Created by Administrator on 2016/12/14.
 */

public class OrderComfirmActivity extends BaseActivity<OrderComfrimContract.Presenter> implements OrderComfrimContract.View, View.OnClickListener {
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
     @BindView(R.id.relative_order_address)
    RelativeLayout mRelativeAddress;//添加配送地址
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
                getPresenter().commit();
                break;
            case R.id.frame_address://添加配送地址
                Intent intent = new Intent(getmActivity(), AddressActivity.class);
                UiUtils.startIntentForResult(getmActivity(),intent,0);
                break;
            case R.id.relative_order_fapiao://发票
                showFapiao();
                break;
            case R.id.relative_order_youhuiquan://代金券
                break;
            case R.id.relative_order_peisong://配送方式
                showpeison();
                break;
            case R.id.bar_back:
                back();
                break;
            case R.id.tv_peisong_ziti:
                getPresenter().ziti();
                 closePopupWindow();
                break;
            case R.id.tv_peisong_kuaidi:
                getPresenter().kuaidi();
                 closePopupWindow();
                break;
            case R.id.tv_fapiao_shi:
                getPresenter().shi();
                 closePopupWindow();
                break;
            case R.id.tv_fapiao_fou:
                 getPresenter().fou();
                 closePopupWindow();
                break;
        }
    }


    public void initRvOrder(RecyclerView.Adapter adapter) {
        mRvConfirmorder.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvConfirmorder.addItemDecoration(new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL_LIST));
        mRvConfirmorder.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && data != null) {
            String name = data.getStringExtra("name");
            String number = data.getStringExtra("number");
            String area = data.getStringExtra("area");
            String address = data.getStringExtra("address");
            getPresenter().setAddress(name, number, area, address);
            mTvOrderNumber.setText(number);
            mTvOrderShouhuoren2.setText(name);
            mTvOrderAddress2.setText(area+address);
            mRelativeAddress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setPeisong(String type) {
        mTvOrderPeisong.setText(type);
    }

    @Override
    public void setFapiao(String fapiao) {
        mTvOrderFapiao.setText(fapiao);
    }


    /**
     * 显示发票
     */
    private void showFapiao() {
        View view = View.inflate(this, R.layout.pp_choose_fapiao, null);
        TextView tv_shi = (TextView) view.findViewById(R.id.tv_fapiao_shi);
        TextView tv_fou = (TextView) view.findViewById(R.id.tv_fapiao_fou);
        tv_shi.setOnClickListener(this);
        tv_fou.setOnClickListener(this);
        setUpPopupWindow(view);
    }
    /**
     * 显示配送
     */
    private void showpeison() {
        View view2 = View.inflate(this, R.layout.pp_choose_peisong, null);
        TextView tv_ziti = (TextView) view2.findViewById(R.id.tv_peisong_ziti);
        TextView tv_kuaidi = (TextView) view2.findViewById(R.id.tv_peisong_kuaidi);
        tv_kuaidi.setOnClickListener(this);
        tv_ziti.setOnClickListener(this);
        setUpPopupWindow(view2);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        UiUtils.backgroundAlpha(1.0f,getmActivity());
    }
}
