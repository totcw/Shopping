package com.betterda.shopping.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.comment.AddCommentActivity;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.javabean.AddComment;
import com.betterda.shopping.javabean.Bus;
import com.betterda.shopping.javabean.OrderComfirm;
import com.betterda.shopping.order.contract.OrderDetailContract;
import com.betterda.shopping.order.presenter.OrderDetailPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

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
     @BindView(R.id.relative_orderdetail_get)
    RelativeLayout mRelativeOrderdetailGet;//立即收货
    @BindView(R.id.linear_orderdetail_payandcancel)
    LinearLayout mLinearOrderdetailPay; //显示立即付款和取消订单
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
        mLoadpagerOrderdetail.setonErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onError();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @OnClick({R.id.relative_orderdetail_delete, R.id.relative_orderdetail_pay,R.id.relative_orderdetail_get,
            R.id.bar_back,R.id.relative_orderdetail_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_orderdetail_delete://取消订单
                getPresenter().cancel();
                break;
            case R.id.relative_orderdetail_pay://立即付款
                getPresenter().pay();
                break;
            case R.id.relative_orderdetail_comment://立即评价
                getPresenter().comment();

                break;
            case R.id.relative_orderdetail_get://立即收货

                getPresenter().get();

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

    @Override
    public void setValue(OrderComfirm data) {
        mTvOrderdetailState.setText(data.getOrderStatus());
        mTvOrderdetailPrice.setText("￥:"+data.getMoney());
        mTvOrderdetailShouhuoren2.setText(data.getName());
        mTvOrderdetailNumber.setText(data.getNumber());
        mTvOrderdetailAddress2.setText(data.getAres()+data.getAddress());
        mTvOrderdetailYunfei.setText(data.getFreight());
        mTvOrderdetailPeisong.setText(data.getType());
        mTvOrderdetailFapiao.setText(data.getBill());
        mTvOrderdetailBiamhao.setText(data.getOrderId());
        mTvOrderdetailTime.setText(data.getTime());
        mRelativeOrderdetailGet.setVisibility(View.GONE);
        mRelativeOrderdetailComment.setVisibility(View.GONE);
        mLinearOrderdetailPay.setVisibility(View.GONE);
        if ("待付款".equals(data.getOrderStatus())) {
            mLinearOrderdetailPay.setVisibility(View.VISIBLE);
        } else if ("待评价".equals(data.getOrderStatus())) {
            mRelativeOrderdetailComment.setVisibility(View.VISIBLE);
        }else if ("待收货".equals(data.getOrderStatus())) {
            mRelativeOrderdetailGet.setVisibility(View.VISIBLE);
        }
        List<Bus> busList = data.getBusList();
        if (busList == null) {
            busList = new ArrayList<>();
        }
        mRvOrderdetail.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvOrderdetail.setAdapter(new CommonAdapter<Bus>(getmActivity(), R.layout.item_recycleview_comfirmorder, busList) {
            @Override
            public void convert(ViewHolder viewHolder, final Bus bus) {
                if (bus != null) {
                    //设置商品信息
                    viewHolder.setText(R.id.tv_item_order_name, bus.getProductName());
                    viewHolder.setText(R.id.tv_item_order_price, "￥" + bus.getSalePrice());
                    viewHolder.setText(R.id.tv_item_order_memberprice, "会员价￥:" + bus.getVipPrice());
                    viewHolder.setText(R.id.tv_item_order_amount, "X " + bus.getTotalCount());
                    ImageView imageView = viewHolder.getView(R.id.sv_item_order);
                    LoadImageFactory.getLoadImageInterface().loadImageFit(getmActivity(), bus.getLittlePicture(), imageView);


                }


            }
        });
    }
}
