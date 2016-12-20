package com.betterda.shopping.order.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.bus.model.Bus;
import com.betterda.shopping.order.contract.BaseOrderContract;
import com.betterda.shopping.order.model.OrderAll;
import com.betterda.shopping.order.presenter.BaseOrderPresenterImpl;
import com.betterda.shopping.utils.UtilMethod;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单的基类
 * Created by Administrator on 2016/12/19.
 */

public class BaseOrderFragment extends BaseFragment<BaseOrderContract.Presenter> implements BaseOrderContract.View {


    @BindView(R.id.layout_recycleview)
    public XRecyclerView mRecycleview;
    @BindView(R.id.layout_loadingpager)
    public LoadingPager mLoadingpager;

    public CommonAdapter<OrderAll> mOrderAllCommonAdapter;
    public List<OrderAll> mOrderAllList;

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

        initRecycleview();
        getData();
    }


    /**
     * 初始化rv
     */
    public void initRecycleview() {
        mRecycleview.setVisibility(View.VISIBLE);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRecycleview.setLoadingMoreEnabled(true);
        mOrderAllCommonAdapter = new CommonAdapter<OrderAll>(getmActivity(), R.layout.item_recycleview_order, mOrderAllList) {
            @Override
            public void convert(ViewHolder holder, OrderAll orderAll) {
                settingView2(holder,orderAll);
            }
        };
        mRecycleview.setAdapter(mOrderAllCommonAdapter);
    }

    public void settingView2(final ViewHolder viewHolder, final OrderAll orderAll) {

        if (null != orderAll) {
            //设置时间
            viewHolder.setText(R.id.tv_item_orderall_time, orderAll.getTime());
            //设置交易类型
            setType(viewHolder, orderAll);
            //初始化商品列表
            loadShop(viewHolder, orderAll, orderAll.getList());
            //先判断有没有付款,没有是待付款的状态
            if ("N".equals(orderAll.getType())) {
                //显示待付款的界面
                show(viewHolder, true, true, true, false,false);
            } else if ("alsend".equals(orderAll.getState())) {
                //显示待收货的界面
                show(viewHolder, true, false, false, true,false);
            } else if ("sign".equals(orderAll.getState())) {
                //显示待评价的界面
                show(viewHolder, true, false, false, false,true);
            } else {
                //全部隐藏
                show(viewHolder, false, false, false, false,false);
            }

            //立即付款
            viewHolder.setOnClickListener(R.id.tv_item_orderall_pay, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  getPay(orderAll.getId());

                }
            });
            //取消订单
            viewHolder.setOnClickListener(R.id.tv_item_orderall_delete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if (!.getmActivity().isFinishing()) {
                        cancelOrderInterface = new CancelOrderInterface() {
                            @Override
                            public void Cancel() {
                                getData3(orderAll.getId());
                            }
                        };
                        if (dialog != null) {
                            dialog.setTitle("确定要取消订单吗?");
                            dialog.show();
                        }
                    }*/

                }
            });
            //立即收货
            viewHolder.setOnClickListener(R.id.tv_item_orderall_shouhuo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               /*     if (!getmActivity().isFinishing()) {
                        cancelOrderInterface = new CancelOrderInterface() {
                            @Override
                            public void Cancel() {
                                getData2(orderAll.getId());
                            }
                        };
                        if (dialog != null) {
                            dialog.setTitle("确定要收货吗?");
                            dialog.show();
                        }
                    }*/
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
                   // startOnDetail(orderAll.getId());
                }
            });

        }
    }

    /**
     * 设置订单的type
     * @param viewHolder
     * @param orderAll
     */
    private void setType(ViewHolder viewHolder, OrderAll orderAll) {
        if ("Y".equals(orderAll.getType())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "交易完成");
        } else if ("N".equals(orderAll.getType())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "待付款");
        }  else if ("sign".equals(orderAll.getState())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "待评价");
        }  else if ("alsend".equals(orderAll.getType())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "待收货");
        }  else if ("send".equals(orderAll.getType())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "待发货");
        }  else if ("take".equals(orderAll.getType())) {
            viewHolder.setText(R.id.tv_item_orderall_type, "待提货");
        } else {
            viewHolder.setText(R.id.tv_item_orderall_type, "交易关闭");
        }
    }

    /**
     * 加载订单的商品列表
     * @param viewHolder
     * @param orderAll
     * @param busList
     */
    private void loadShop(ViewHolder viewHolder, OrderAll orderAll, final List<Bus> busList) {
        if (null != busList) {
            //设置商品的件数
            viewHolder.setText(R.id.tv_item_orderall_amount2, "共" + UtilMethod.addAmount(busList) + "件商品");
            //设置商品的价格
            viewHolder.setText(R.id.tv_item_orderall_money, "￥" + orderAll.getAmount());
            RecyclerView rv = viewHolder.getView(R.id.rv_order_item);
            rv.setLayoutManager(new LinearLayoutManager(getmActivity()));
            rv.setAdapter(new CommonAdapter<Bus>(getmActivity(), R.layout.item_recycleview_comfirmorder, busList) {
                @Override
                public void convert(ViewHolder viewHolder, final Bus bus) {
                    if (bus != null) {
                        //设置商品信息
                        viewHolder.setText(R.id.tv_item_order_name, bus.getName());
                        viewHolder.setText(R.id.tv_item_order_price, "￥" + bus.getMoney());
                        viewHolder.setText(R.id.tv_item_order_amount, "X " + bus.getAmount());
                        if (!TextUtils.isEmpty(bus.getUrl())) {
                            //TODO 加载图标
                        }
                        //订单详情
                        viewHolder.setOnClickListener(R.id.linear_comfirmorder2, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // startOnDetail(orderAll.getId());
                            }
                        });
                        //显示评价按钮
                       /* if ("sign".equals(orderAll.getState())) {

                            if ("N".equals(bus.getIsComment())) {
                                viewHolder.setVisible(R.id.tv_item_comfirmorder_comment, true);
                            } else {
                                viewHolder.setVisible(R.id.tv_item_comfirmorder_comment, false);
                            }
                        }*/
                        //添加评价
                        viewHolder.setOnClickListener(R.id.tv_item_comfirmorder_comment, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                               /* Intent intent = new Intent(getmActivity(), AddCommentActivity.class);
                                intent.putExtra("orderid", orderAll.getId());
                                intent.putExtra("productid", bus.getId());
                                intent.putExtra("shopId", bus.getShopId());
                                getmActivity().startActivity(intent);*/

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
    public  void show(ViewHolder viewHolder, boolean isshow, boolean show, boolean show2, boolean show3,boolean show4) {
        viewHolder.setVisible(R.id.relative_order_delete, isshow);
        viewHolder.setVisible(R.id.tv_item_orderall_pay, show);
        viewHolder.setVisible(R.id.tv_item_orderall_delete, show2);
        viewHolder.setVisible(R.id.tv_item_orderall_shouhuo, show3);
        viewHolder.setVisible(R.id.tv_item_orderall_comment, show4);
    }


    /**
     * 立即付款
     *
     */
    private void getPay( String orderid) {


    }


    /**
     * 跳转至详情
     * @param orderid
     */
    private void startOnDetail(String orderid) {

    }


    /**
     * 获取数据
     */
    public void getData() {

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
