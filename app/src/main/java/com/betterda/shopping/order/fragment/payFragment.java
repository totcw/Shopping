package com.betterda.shopping.order.fragment;

import com.betterda.shopping.order.model.OrderAll;

/**
 * 待付款订单
 * Created by Administrator on 2016/12/19.
 */

public class PayFragment extends BaseOrderFragment {

    @Override
    public void getData() {
        super.getData();
        for ( int i=0;i<6;i++) {
            mOrderAllList.add(new OrderAll());
        }
        mOrderAllCommonAdapter.notifyDataSetChanged();
    }
}
