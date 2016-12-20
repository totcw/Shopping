package com.betterda.shopping.order.fragment;

import com.betterda.shopping.order.model.OrderAll;

/**
 * 待发货订单
 * Created by Administrator on 2016/12/19.
 */

public class SendFragment extends BaseOrderFragment {
    @Override
    public void getData() {
        super.getData();
        for ( int i=0;i<7;i++) {
            mOrderAllList.add(new OrderAll());
        }
        mOrderAllCommonAdapter.notifyDataSetChanged();
    }
}
