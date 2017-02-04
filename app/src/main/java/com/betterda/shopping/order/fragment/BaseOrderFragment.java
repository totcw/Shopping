package com.betterda.shopping.order.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.Bus;
import com.betterda.shopping.javabean.OrderComfirm;
import com.betterda.shopping.order.OrderDetailActivity;
import com.betterda.shopping.order.contract.BaseOrderContract;
import com.betterda.shopping.order.presenter.BaseOrderPresenterImpl;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单的基类
 * Created by Administrator on 2016/12/19.
 */

public class BaseOrderFragment extends BaseFragment<BaseOrderContract.Presenter> implements BaseOrderContract.View {


    @BindView(R.id.layout_recycleview)
    public XRecyclerView mRecycleview;
    @BindView(R.id.layout_loadingpager)
    public LoadingPager mLoadingpager;

    public CommonAdapter<OrderComfirm> mOrderAllCommonAdapter;
    public List<OrderComfirm> mOrderAllList;
    public String orderStatus;//订单状态
    public int pageNo=1;//页数

    @Override
    protected BaseOrderContract.Presenter onLoadPresenter() {
        return new BaseOrderPresenterImpl();
    }

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_recycleview, null);
    }

    @Override
    public void initData() {
        super.initData();
        mOrderAllList = new ArrayList<>();
        getData();
    }


    /**
     * 初始化rv
     */
    public void initRecycleview() {
        mRecycleview.setVisibility(View.VISIBLE);
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRecycleview.setLoadingMoreEnabled(true);
        mOrderAllCommonAdapter = new CommonAdapter<OrderComfirm>(getmActivity(), R.layout.item_recycleview_order, mOrderAllList) {
            @Override
            public void convert(ViewHolder holder, OrderComfirm OrderComfirm) {
                settingView2(holder, OrderComfirm);
            }
        };
        mRecycleview.setAdapter(mOrderAllCommonAdapter);
    }

    public void settingView2(final ViewHolder viewHolder, final OrderComfirm OrderComfirm) {

        if (null != OrderComfirm) {
            //设置时间
            viewHolder.setText(R.id.tv_item_orderall_time, OrderComfirm.getTime());
            //设置订单状态
            setType(viewHolder, OrderComfirm);
            //初始化商品列表
            loadShop(viewHolder, OrderComfirm, OrderComfirm.getBusList());
            //先判断有没有付款,没有是待付款的状态
            if ("待付款".equals(OrderComfirm.getOrderStatus())) {
                //显示待付款的界面
                show(viewHolder, true, true, true, false, false);
            } else if ("待收货".equals(OrderComfirm.getOrderStatus())) {
                //显示待收货的界面
                show(viewHolder, true, false, false, true, false);
            } else if ("待评价".equals(OrderComfirm.getOrderStatus())) {
                //显示待评价的界面
                show(viewHolder, true, false, false, false, true);
            } else {
                //全部隐藏
                show(viewHolder, false, false, false, false, false);
            }

            //立即付款
            viewHolder.setOnClickListener(R.id.tv_item_orderall_pay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPay(OrderComfirm.getOrderId());

                }
            });
            //取消订单
            viewHolder.setOnClickListener(R.id.tv_item_orderall_delete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            //立即收货
            viewHolder.setOnClickListener(R.id.tv_item_orderall_shouhuo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            //立即评价
            viewHolder.setOnClickListener(R.id.tv_item_orderall_comment, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //订单详情
            viewHolder.setOnClickListener(R.id.linear_comfirmorder, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startOnDetail(OrderComfirm.getOrderId());
                }
            });

        }
    }

    /**
     * 设置订单的type
     *
     * @param viewHolder
     */
    private void setType(ViewHolder viewHolder, OrderComfirm OrderComfirm) {

        viewHolder.setText(R.id.tv_item_orderall_type, OrderComfirm.getOrderStatus());

    }

    /**
     * 加载订单的商品列表
     *
     * @param viewHolder
     * @param busList
     */
    private void loadShop(ViewHolder viewHolder, final OrderComfirm OrderComfirm, final List<Bus> busList) {
        if (null != busList) {
            //设置商品的件数
            viewHolder.setText(R.id.tv_item_orderall_amount2, "共" + UtilMethod.addAmount(busList) + "件商品");
            //设置商品的价格
            viewHolder.setText(R.id.tv_item_orderall_money, "￥" + OrderComfirm.getMoney());
            RecyclerView rv = viewHolder.getView(R.id.rv_order_item);
            rv.setLayoutManager(new LinearLayoutManager(getmActivity()));
            rv.setAdapter(new CommonAdapter<Bus>(getmActivity(), R.layout.item_recycleview_comfirmorder, busList) {
                @Override
                public void convert(ViewHolder viewHolder, final Bus bus) {
                    if (bus != null) {
                        //设置商品信息
                        viewHolder.setText(R.id.tv_item_order_name, bus.getProductName());
                        viewHolder.setText(R.id.tv_item_order_price, "￥" + bus.getSalePrice());
                        viewHolder.setText(R.id.tv_item_order_memberprice, "会员价￥:" + bus.getVipPrice());
                        viewHolder.setText(R.id.tv_item_order_amount, "X " + bus.getTotalCount());
                        ImageView imageView = viewHolder.getView(R.id.sv_item_order);
                        LoadImageFactory.getLoadImageInterface().loadImageCrop(getmActivity(),bus.getLittlePicture(),imageView);
                        if (!TextUtils.isEmpty(bus.getLittlePicture())) {
                            //TODO 加载图标
                        }
                        //订单详情
                        viewHolder.setOnClickListener(R.id.linear_comfirmorder2, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startOnDetail(OrderComfirm.getOrderId());
                            }
                        });

                        //添加评价
                        viewHolder.setOnClickListener(R.id.tv_item_comfirmorder_comment, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                            }
                        });
                    }


                }
            });
        }
    }


    /**
     * 显示付款界面
     *
     * @param viewHolder
     */
    public void show(ViewHolder viewHolder, boolean isshow, boolean show, boolean show2, boolean show3, boolean show4) {
        viewHolder.setVisible(R.id.relative_order_delete, isshow);
        viewHolder.setVisible(R.id.tv_item_orderall_pay, show);
        viewHolder.setVisible(R.id.tv_item_orderall_delete, show2);
        viewHolder.setVisible(R.id.tv_item_orderall_shouhuo, show3);
        viewHolder.setVisible(R.id.tv_item_orderall_comment, show4);
    }


    /**
     * 立即付款
     */
    private void getPay(String orderid) {


    }


    /**
     * 跳转至详情
     *
     * @param orderid
     */
    private void startOnDetail(String orderid) {
        Intent intent = new Intent(getmActivity(), OrderDetailActivity.class);
        intent.putExtra("orderId", orderid);
        UiUtils.startIntent(getmActivity(), intent);

    }


    /**
     * 获取数据
     */
    public void getData() {

        getRxManager().add(NetWork.getNetService()
                .getOrder(getAccount(), getToken(), orderStatus, pageNo + "", Constants.PAGESIZE)
                .compose(NetWork.handleResult(new BaseCallModel<List<OrderComfirm>>()))
                .subscribe(new MyObserver<List<OrderComfirm>>() {
                    @Override
                    protected void onSuccess(List<OrderComfirm> data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("获取订单列表success:" + data.toString());
                        }

                        if (mOrderAllList != null && mOrderAllCommonAdapter != null) {
                            mOrderAllList.clear();
                            mOrderAllList.addAll(data);
                            // mOrderAllCommonAdapter.notifyDataSetChanged();
                        }
                        initRecycleview();
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("获取订单列表fail:" + resultMsg);
                        }
                    }

                    @Override
                    public void onExit() {

                    }
                }));
    }

    /**
     * 立即收货
     */
    private void getData2(String orderid) {


    }

    /**
     * 取消订单
     *
     * @param orderid
     */
    private void getData3(String orderid) {

    }
}
